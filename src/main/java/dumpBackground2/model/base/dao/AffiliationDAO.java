package dumpBackground2.model.base.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dumpBackground2.baseevents.event.BaseEvent;
import dumpBackground2.baseevents.BaseEventsReceiver;
import dumpBackground2.baseevents.event.refresh.RefreshAffiliationEvent;
import dumpBackground2.model.base.BaseManager;
import dumpBackground2.model.base.entity.Affiliation;
import dumpBackground2.model.base.entity.CustomApplication;
import dumpBackground2.model.base.entity.Token;
import dumpBackground2.model.base.exception.BaseException;
import dumpBackground2.project.Context;
import dumpBackground2.project.observer.StateObserver;
import dumpBackground2.tools.Regex;
import dumpBackground2.tools.Util;

public class AffiliationDAO implements StateObserver {
	private static final Logger logger = LoggerFactory.getLogger(AffiliationDAO.class);
	/***************************************************************************/
	/* Private attributes */
	/***************************************************************************/

	/**
	 * The knowlege base manager
	 */
	private Token token;
	private BaseManager kbm;
	private final static String REPORT_TABLE_NAME = "RPRT_REPORT";

	/**
	 * <pre>
	 * INSERT INTO Affiliation(source,conferenceInformation) VALUES(?,?);
	 * </pre>
	 */
	private final static String __INSERT_JOURNAL = "INSERT into " + REPORT_TABLE_NAME
			+ " (name,display_name,description,disabled,STRD_PRCDR) VALUES(''{0}'',''{1}'',''{2}'',{3},{4})";

	/**
	 * <pre>
	 * INSERT INTO Affiliation(idJournal,source,conferenceInformation) VALUES(?,?,?);
	 * </pre>
	 */
	private final static String __INSERT_JOURNAL_WITH_ID = "INSERT INTO cars(id,name) VALUES(?,?)";

	/**
	 * <pre>
	 * DELETE Affiliation
	 * WHERE idJournal = ?;
	 * </pre>
	 */
	private final static String __REMOVE_JOURNAL = "DELETE FROM " + REPORT_TABLE_NAME + " WHERE id = {0}";

	/**
	 * <pre>
	 * UPDATE Affiliation SET source = ? WHERE idJournal = ?;
	 * </pre>
	 */
	private final static String __UPDATE_SOURCE = "UPDATE  " + REPORT_TABLE_NAME + " SET source = ''{0}'' "
			+ "WHERE id = {1}";

	/**
	 * <pre>
	 * UPDATE Affiliation SET conferenceInformation = ? WHERE idJournal = ?;
	 * </pre>
	 */
	private final static String __UPDATE_CONFERENCE_INFORMATION = "UPDATE Affiliation "
			+ "SET conferenceInformation = ? " + "WHERE idJournal = ?;";

	/**
	 * <pre>
	 * UPDATE Affiliation
	 * SET source = ?,
	 *     conferenceInformation = ?
	 * WHERE idJournal = ?;
	 * </pre>
	 */
	private final static String __UPDATE_JOURNAL = "UPDATE  " + REPORT_TABLE_NAME + " SET description = ''{0}'', "
			+ "    display_name = ''{1}'' " + "WHERE id = {2}";

	/**
	 * <pre>
	 * </pre>
	 */
	private final static String __SELECT_DOCUMENT = "SELECT d.* " + "FROM Document d, Affiliation j "
			+ "WHERE j.idJournal = ? AND " + "      d.Journal_idJournal = j.idJournal;";

	/**
	 * <pre>
	 * SELECT j.idJournal, j.source, j.conferenceInformation,
	 *        s.idSubjectCategory, s.subjectCategoryName
	 *        p.idPublishDate, p.year, p.date
	 * FROM PublishDate p, Affiliation j, SubjectCategory s, Journal_SubjectCategory_PublishDate jsp
	 * WHERE j.idJournal = ? AND
	 *       j.idJournal = jsp.Journal_idJournal AND
	 *       jsp.SubjectCategory_idSubjectCategory = s.idSubjectCategory AND
	 *       jsp.PublishDate_idPublishDate = p.idPublishDate;
	 * </pre>
	 */

	private final static String __SELECT_JOURNAL_SUBJECTCATEGORIES_PUBLISHDATE = "SELECT j.*, s.*, p.* "
			+ "FROM PublishDate p, Affiliation j, SubjectCategory s, Journal_SubjectCategory_PublishDate jsp "
			+ "WHERE j.idJournal = ? AND " + "      j.idJournal = jsp.Journal_idJournal AND "
			+ "      jsp.SubjectCategory_idSubjectCategory = s.idSubjectCategory AND "
			+ "      jsp.PublishDate_idPublishDate = p.idPublishDate;";

	private final static String __SELECT_SUBJECTCATEGORIES = "SELECT s.* "
			+ "FROM SubjectCategory s, Journal_SubjectCategory_PublishDate jsp "
			+ "WHERE jsp.Journal_idJournal = ? AND "
			+ "      jsp.SubjectCategory_idSubjectCategory = s.idSubjectCategory;";

	private final static String __SELECT_JOURNAL_BY_ID = "SELECT * FROM " + REPORT_TABLE_NAME + " WHERE id = {0}";
	private final static String __SELECT_JOURNAL_BY_SOURCE = "SELECT * FROM Affiliation WHERE source = ?;";
	private final static String __SELECT_JOURNALS = "SELECT * FROM RPRT_REPORT";
	private final static String __CHECK_JOURNAL_BY_SOURCE = "SELECT id FROM " + REPORT_TABLE_NAME
			+ " WHERE description = ''{0}''";
	private final static String __CHECK_JOURNAL_BY_ID = "SELECT name FROM CARS WHERE id = 1";

	private Statement statCheckJournalById;
	private Statement statCheckJournalBySource;
	private Statement statAddJournal;
	private PreparedStatement statAddJournalWithId;

	private PreparedStatement statSelectDocuments;
	private Statement statSelectJournals;
	private Statement statSelectJournalById;
	private PreparedStatement statSelectJournalBySource;
	private PreparedStatement statSelectJournalSubjectCategoryPublishDate;
	private PreparedStatement statSelectSubjectCategories;
	private PreparedStatement statCheckAffiliationByFullAffiliation;
	private PreparedStatement statUpdateFullAffiliation;
	private Statement statUpdateConferenceInformation;
	private PreparedStatement statUpdateJournal;
	private Statement statUpdateSource;
	private Statement statement;

	/***************************************************************************/
	/* Constructors */
	/***************************************************************************/

	/**
	 *
	 * @param kbm
	 */
	public AffiliationDAO() {
		Context.getInstance().addKnowledgeBaseStateObserver(this);
	}

	public AffiliationDAO(BaseManager kbm) throws BaseException {

		this.kbm = kbm;

		try {
			this.statCheckAffiliationByFullAffiliation = this.kbm.getConnection()
					.prepareStatement("Select * from cars");

			this.statUpdateFullAffiliation = this.kbm.getConnection().prepareStatement("Select * from cars");
			this.statCheckJournalById = this.kbm.getConnection().prepareStatement(__CHECK_JOURNAL_BY_ID);
			// this.statCheckJournalBySource =
			// this.kbm.getConnection().prepareStatement(__CHECK_JOURNAL_BY_SOURCE);
			this.statAddJournalWithId = this.kbm.getConnection().prepareStatement(__INSERT_JOURNAL_WITH_ID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * try {
		 * 
		 * this.statCheckJournalById =
		 * this.kbm.getConnection().prepareStatement(__CHECK_JOURNAL_BY_ID);
		 * this.statCheckJournalBySource =
		 * this.kbm.getConnection().prepareStatement(__CHECK_JOURNAL_BY_SOURCE); //
		 * this.statAddJournal =
		 * this.kbm.getConnection().prepareStatement(__INSERT_JOURNAL,
		 * Statement.RETURN_GENERATED_KEYS); // this.statAddJournalWithId =
		 * this.kbm.getConnection().prepareStatement(__INSERT_JOURNAL_WITH_ID);
		 * this.statRemoveJournal =
		 * this.kbm.getConnection().prepareStatement(__REMOVE_JOURNAL);
		 * this.statSelectDocuments =
		 * this.kbm.getConnection().prepareStatement(__SELECT_DOCUMENT);
		 * 
		 * 
		 * this.statSelectJournalBySource =
		 * this.kbm.getConnection().prepareStatement(__SELECT_JOURNAL_BY_SOURCE);
		 * this.statSelectJournalSubjectCategoryPublishDate =
		 * this.kbm.getConnection().prepareStatement(
		 * __SELECT_JOURNAL_SUBJECTCATEGORIES_PUBLISHDATE);
		 * this.statSelectSubjectCategories =
		 * this.kbm.getConnection().prepareStatement(__SELECT_SUBJECTCATEGORIES);
		 * 
		 * this.statUpdateJournal =
		 * this.kbm.getConnection().prepareStatement(__UPDATE_JOURNAL);
		 * this.statUpdateSource =
		 * this.kbm.getConnection().prepareStatement(__UPDATE_SOURCE);
		 * 
		 * } catch (SQLException e) {
		 * 
		 * throw new BaseException(e.getMessage(), e.getCause()); }
		 */
	}

	/***************************************************************************/
	/* Public Methods */
	/***************************************************************************/

	/**
	 *
	 * @param source
	 * @param conferenceInformation
	 * @return
	 * @throws BaseException
	 */
	public Long addJournal(String source, String conferenceInformation, boolean notifyObservers) throws BaseException {
		logger.info("addJournal_3");
		Affiliation report = new Affiliation();
		/*
		 * report.setDescription(source); report.setDisabled(0); report.setSpId(2L);
		 */
		Long id = addJournal(report, notifyObservers);
		return id;
	}

	/**
	 *
	 * @param source
	 * @param conferenceInformation
	 * @return
	 * @throws BaseException
	 */
	public Long addJournal(Long journalID, String source, boolean notifyObservers) throws BaseException {
		logger.info("addJournal_2");
		Affiliation report = new Affiliation();	
		Long id = addJournal(report, notifyObservers);
		logger.info("checkPost_2");

		return id;
	}

	public List<Affiliation> getAffiliations(Token token) {
		logger.info("get_affiliations");	
		logger.info("get_affiliations2");
		logger.info("get_affiliations_test_start");
		List<Affiliation> affiliations = new ArrayList<Affiliation>();
		String baseUrl = Context.getInstance().getBaseUrl();
		Token localToken = token;
		logger.info("baseUrl:" + baseUrl);
		logger.info("accessToken:" + localToken);
		if (null != localToken) {
			String accessToken = localToken.getAccessToken();
			logger.info("i here");
			if (Util.isNotNullOrEmpty(accessToken)) {
				Client client = ClientBuilder.newClient();
				String apiURL = baseUrl + "/myOAuthAPI/test2";
				Response response1 = (Response) client.target(apiURL). 			​
				request("application/json;charset=UTF-8"). // Request type
				accept("application/json;charset=UTF-8"). // Response access type - application/scim+json
				header("Authorization", accessToken).get(); // header with access token as authorization value
				List<CustomApplication> output = response1.readEntity(new GenericType<List<CustomApplication>>() {}); // reading response as string format
				if(Util.isNotNullOrEmpty(output)) {
					int counter = 0;
					for(CustomApplication customApplication : output) {
						counter++;
						Affiliation affiliation = new Affiliation(counter, customApplication.getName(), counter, counter);
						affiliations.add(affiliation);
					}
				}
			}
		}
		
		BaseEventsReceiver.getInstance().addEvent(new RefreshAffiliationEvent());
		return affiliations;
	}



	/**
	 * 
	 * @param journal
	 * @return
	 * @throws BaseException
	 */
	public Long addJournal(Affiliation report, boolean notifyObservers) throws BaseException {
		logger.info("addJournal_1");

		Long id = -1L;

		Connection connection = getConnection();
		this.statement = createStatement(connection);
		// logger.info("checkPost_1");
		try {

			String resultQuery = QueryParser.buildQueryString(__INSERT_JOURNAL, Regex.getParenthesespattern(),
					Affiliation.class, report);
			// if (this.statement.executeUpdate(resultQuery,new String[] { "ID" }) == 1) {
			if (this.statement.executeUpdate(resultQuery, Statement.RETURN_GENERATED_KEYS) == 1) {

				// id = this.statement.getGeneratedKeys().getLong(0);
				try (ResultSet keys = statement.getGeneratedKeys()) {

					while (keys.next()) {

						id = keys.getLong(1);
					}

				}
				/*
				 * id= (Long) keys.next().getObject(1); id= keys.getLong(1); }
				 */
				// this.statement.getGeneratedKeys().close();
				// id=2L;

			} else {

				id = null;
			}

		} catch (SQLException e) {

			throw new BaseException(e.getMessage(), e.getCause());
		}

		// Notify to the observer
		if (notifyObservers) {

			// EventsReceiver.getInstance().addEvent(new AddJournalEvent(getJournal(id)));
			// EventsReceiver.getInstance().addEvent(new RefreshJournalEvent());
		}

		return id;
	}

	public boolean checkAffiliation(String fullAffiliation) throws BaseException {

		boolean result = false;
		ResultSet rs;

		try {

			this.statCheckAffiliationByFullAffiliation.clearParameters();

			this.statCheckAffiliationByFullAffiliation.setString(1, fullAffiliation);

			rs = this.statCheckAffiliationByFullAffiliation.executeQuery();

			result = rs.next();

			rs.close();

			return result;

		} catch (SQLException e) {

			throw new BaseException(e.getMessage(), e.getCause());
		}
	}

	/**
	 *
	 * @param journalID
	 * @return
	 * @throws BaseException
	 */
	public boolean removeJournal(Integer journalID, boolean notifyObservers) throws BaseException {

		boolean result = false;
		Affiliation report = null;

		String queryTemplate = __REMOVE_JOURNAL;
		// Save the information before remove
		if (notifyObservers) {

			report = getJournal(journalID);

		}

		try {

			String resultQuery = QueryParser.buildQueryString(queryTemplate, Regex.getCurlybracespattern(),
					Affiliation.class, report);

			this.statement = createStatement(getConnection());

			result = this.statement.executeUpdate(resultQuery) > 0;

		} catch (SQLException e) {

			throw new BaseException(e.getMessage(), e.getCause());
		}

		// Notify to the observer
		if (notifyObservers) {

			// EventsReceiver.getInstance().addEvent(new RemoveJournalEvent(report));
			// EventsReceiver.getInstance().addEvent(new RefreshJournalEvent());
			// EventsReceiver.getInstance().addEvent(new
			// UpdateDocumentEvent(CurrentProject.getInstance().getFactoryDAO().getDocumentDAO().refreshDocuments(documents)));
			// EventsReceiver.getInstance().addEvent(new JournalRelationDocumentEvent());
		}

		return result;
	}

	/**
	 *
	 * @param idJournal the journal's ID
	 *
	 * @return a <ocde>Affiliation</code> or null if there is not any journal with
	 *         this ID
	 *
	 * @throws BaseException
	 */
	public Affiliation getJournal(Integer idJournal) throws BaseException {

		logger.info("get journal:" + idJournal);
		Affiliation report = null;
		Connection connection = getConnection();
		try {
			report = new Affiliation();
			report.setAffiliationID(idJournal);
			String resultQuery = QueryParser.buildQueryString(__SELECT_JOURNAL_BY_ID, Regex.getCurlybracespattern(),
					Affiliation.class, report);

			List<Affiliation> reports = ReflectionExHelper.selectQuery(Affiliation.class, connection, resultQuery);
			report = Util.getFirstElement(reports);
		} catch (SQLException e) {

			throw new BaseException(e.getMessage(), e.getCause());
		}

		return report;
	}

	/**
	 *
	 * @param source the journal's suorce
	 *
	 * @return a <ocde>Affiliation</code> or null if there is not any journal with
	 *         this source
	 *
	 * @throws BaseException
	 */
	public Affiliation getJournal(String source) throws BaseException {

		ResultSet rs;
		Affiliation report = null;

		try {

			report = new Affiliation();
			// report.setDescription(source);
			Connection connection = getConnection();

			String resultQuery = QueryParser.buildQueryString(__SELECT_JOURNAL_BY_SOURCE, Regex.getCurlybracespattern(),
					Affiliation.class, report);

			List<Affiliation> reports = ReflectionExHelper.selectQuery(Affiliation.class, connection, resultQuery);
			report = Util.getFirstElement(reports);

		} catch (SQLException e) {

			throw new BaseException(e.getMessage(), e.getCause());
		}

		return report;
	}

	/**
	 * 
	 * @return a <ocde>Affiliation</code> or null if there is not any journal with
	 *         this ID
	 *
	 * @throws BaseException
	 */
	public List<Affiliation> getJournals() throws BaseException {
		logger.info("get journals");
		ResultSet resultSet = null;
		List<Affiliation> journalList = new ArrayList<Affiliation>();

		try {

			Connection connection = getConnection();
			journalList = ReflectionExHelper.selectQuery(Affiliation.class, connection, __SELECT_JOURNALS);

		} catch (SQLException e) {

			throw new BaseException(e.getMessage(), e.getCause());
		}

		return journalList;
	}

	/**
	 * 
	 * @param journalID
	 * @param source
	 * @return
	 * @throws BaseException
	 */
	public boolean setSource(Integer journalID, String source, boolean notifyObservers) throws BaseException {

		boolean result = false;

		try {
			Affiliation report = new Affiliation();
			report.setAffiliationID(journalID);
//report.setDescription(source);
			String resultQuery = QueryParser.buildQueryString(__UPDATE_SOURCE, Regex.getCurlybracespattern(),
					Affiliation.class, report);

			result = this.statUpdateSource.executeUpdate(resultQuery) > 0;

		} catch (SQLException e) {

			throw new BaseException(e.getMessage(), e.getCause());

		}

		// Notify to the observer
		if (notifyObservers) {

			// EventsReceiver.getInstance().addEvent(new
			// UpdateJournalEvent(getJournal(journalID)));
			// EventsReceiver.getInstance().addEvent(new
			// UpdateDocumentEvent(getDocuments(journalID)));
		}

		return result;
	}

	/**
	 * 
	 * @param journalID
	 * @param conferenceInformation
	 * @return
	 * @throws BaseException
	 */
	public boolean setConferenceInformation(Long journalID, String conferenceInformation, boolean notifyObservers)
			throws BaseException {

		boolean result = false;
		Affiliation report = new Affiliation();
//report.setAffiliationID(journalID);
//report.setDisplayName(conferenceInformation);
		try {

			String resultQuery = QueryParser.buildQueryString(__UPDATE_CONFERENCE_INFORMATION,
					Regex.getCurlybracespattern(), Affiliation.class, report);

			result = this.statUpdateConferenceInformation.executeUpdate(resultQuery) > 0;

		} catch (SQLException e) {

			throw new BaseException(e.getMessage(), e.getCause());

		}

		// Notify to the observer
		if (notifyObservers) {

			// EventsReceiver.getInstance().addEvent(new
			// UpdateJournalEvent(getJournal(journalID)));
		}

		return result;
	}

	public boolean setFullAffiliation(Integer affiliationID, String fullAffilliation, boolean notifyObservers)
			throws BaseException {

		boolean result = false;

		try {

			// this.statUpdateFullAffiliation.clearParameters();

			// this.statUpdateFullAffiliation.setString(1, fullAffilliation);
			// this.statUpdateFullAffiliation.setInt(2, affiliationID);

			result = this.statUpdateFullAffiliation.executeUpdate() > 0;

		} catch (SQLException e) {

			// throw new KnowledgeBaseException(e.getMessage(), e.getCause());

		}

		// Notify to the observer
		if (notifyObservers) {

			// KnowledgeBaseEventsReceiver.getInstance().addEvent(new
			// UpdateAffiliationEvent(getAffiliation(affiliationID)));
		}

		return result;
	}

	/**
	 * 
	 * @param journalID
	 * @param source
	 * @param conferenceInformation
	 * @return
	 * @throws BaseException
	 */
	public boolean updateJournal(Long journalID, String source, boolean notifyObservers) throws BaseException {
		logger.info("update journal");
		boolean result = false;
		Affiliation report = new Affiliation();
		// report.setAffiliationID(journalID);
		// report.setDescription(source);
		// report.setDisplayName("new display_name");
		try {
			String resultQuery = QueryParser.buildQueryString(__UPDATE_JOURNAL, Regex.getCurlybracespattern(),
					Affiliation.class, report);
			this.statement = createStatement(getConnection());
			result = this.statement.executeUpdate(resultQuery) > 0;

		} catch (SQLException e) {

			throw new BaseException(e.getMessage(), e.getCause());

		}

		// Notify to the observer
		if (notifyObservers) {

			// EventsReceiver.getInstance().addEvent(new
			// UpdateJournalEvent(getJournal(journalID)));
			// EventsReceiver.getInstance().addEvent(new RefreshJournalEvent());
			// EventsReceiver.getInstance().addEvent(new
			// UpdateDocumentEvent(getDocuments(journalID)));
		}

		return result;
	}

	/**
	 *
	 * @return an array with the documents associated with this journal
	 *
	 * @throws BaseException
	 */

	/**
	 *
	 * @return an array with the authors associated with this document
	 *
	 * @throws BaseException
	 */

	/**
	 * <p>
	 * Check if there is a <code>Affiliation</code> with this source.
	 * </p>
	 *
	 * @param source a string with the journal's source
	 *
	 * @return true if there is an <code>Affiliation</code> with this attribute
	 *
	 * @throws BaseException if a database access error occurs
	 */
	public boolean checkJournal(String source) throws BaseException {
		logger.info("check_journal_by_source");
		boolean result = false;
		ResultSet rs;
		Affiliation report = new Affiliation();
		try {
			// logger.info("source: "+source);
			// report.setDescription(source);
			String resultQuery = QueryParser.buildQueryString(__CHECK_JOURNAL_BY_SOURCE, Regex.getCurlybracespattern(),
					Affiliation.class, report);
			// logger.info("result_query: "+resultQuery);
			Connection connection = getConnection();

			List<Affiliation> reports = ReflectionExHelper.selectQuery(Affiliation.class, connection, resultQuery);
			if (Util.isNotNullOrEmpty(reports)) {
				result = true;
			}

			return result;

		} catch (SQLException e) {

			throw new BaseException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * <p>
	 * Check if there is a <code>Affiliation</code> with this ID.
	 * </p>
	 *
	 * @param idJournal the journal's ID
	 *
	 * @return true if there is an <code>Affiliation</code> with this attribute
	 *
	 * @throws BaseException if a database access error occurs
	 */

	private Connection getConnection() {
		return this.kbm.getConnection();
	}

	private Statement createStatement(Connection inConnection) {
		Connection connection = inConnection;
		Statement statement = null;
		if (null != connection) {
			try {
				statement = connection.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return statement;
	}

	public boolean checkJournal(Integer idJournal) throws BaseException {
		logger.info("checkJournal_1");
		boolean result = false;

		Affiliation report = new Affiliation();
		Connection connection = getConnection();
		this.statement = createStatement(connection);

		try {

			report.setAffiliationID(idJournal);
			String resultQuery = QueryParser.buildQueryString(__CHECK_JOURNAL_BY_ID, Regex.getCurlybracespattern(),
					Affiliation.class, report);

			List<Affiliation> reports = ReflectionExHelper.selectQuery(Affiliation.class, connection, resultQuery);
			if (Util.isNotNullOrEmpty(reports)) {
				result = true;
			}

			return result;

		} catch (SQLException e) {

			throw new BaseException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * 
	 * @param journalsToRefresh
	 * @return
	 * @throws BaseException
	 */
	public ArrayList<Affiliation> refreshJournals(ArrayList<Affiliation> journalsToRefresh) throws BaseException {

		int i;
		String query;
		ResultSet rs;
		ArrayList<Affiliation> reports = new ArrayList<>();

		i = 0;

		if (!journalsToRefresh.isEmpty()) {

			query = "SELECT * FROM Affiliation WHERE idJournal IN (" + journalsToRefresh.get(i).getAffiliationID();

			for (i = 1; i < journalsToRefresh.size(); i++) {

				query += ", " + journalsToRefresh.get(i).getAffiliationID();
			}

			query += ");";

			try {

				Connection connection = getConnection();
				Statement stat = createStatement(connection);
				reports = (ArrayList<Affiliation>) ReflectionExHelper.selectQuery(Affiliation.class, connection, query);
				rs = stat.executeQuery(query);

			} catch (SQLException e) {

				throw new BaseException(e.getMessage(), e.getCause());
			}
		}

		return reports;
	}

	/**
	 * 
	 * @param journalToRefresh
	 * @return
	 * @throws BaseException
	 */
	public Affiliation refreshJournal(Affiliation journalToRefresh) throws BaseException {
		logger.info("refresh journal:" + journalToRefresh.getAffiliationID());
		return getJournal(journalToRefresh.getAffiliationID());
	}

	@Override
	public void stateChanged(boolean loaded) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tokenRefreshed(Token token) {
		// TODO Auto-generated method stub
		logger.info("got token");
		setAccessToken(token);
	}

	public Token getAccessToken() {
		return token;
	}

	public void setAccessToken(Token accessToken) {
		this.token = accessToken;
	}

	/***************************************************************************/
	/* Private Methods */
	/***************************************************************************/

}

package mp.procurement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mp.procurement.model.Bid;
import mp.procurement.model.BidCount;
import mp.procurement.model.Division;
import mp.procurement.model.Financials;
import mp.procurement.model.Login;
import mp.procurement.model.Lot;
import mp.procurement.model.LotData;
import mp.procurement.model.LotList;
import mp.procurement.model.LotRate;
import mp.procurement.model.Party;
import mp.procurement.model.PartyLots;
import mp.procurement.model.ReportBean;
import mp.procurement.model.ResultBid;
import mp.procurement.model.Tender;



@Repository
@Transactional
public class CommonDao {

	@Autowired
	private SessionFactory sessionFactory;


	public int saveEntity(Object entity) throws Exception {

		try {
			this.sessionFactory.getCurrentSession().save(entity);
			return 1;
		} catch (Exception e) {
			throw new Exception();
		}
	}
	
	
	
	
	public int saveMultipleEntity(List<Bid> list) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			for (Bid b : list){
				session.save(b);	
			}
			return 1;
		} catch (Exception e) {
			throw new Exception();
		}
	}
	
	public List<Party> getAllParty() throws Exception{
		try{
			List<Party> list = this.sessionFactory.getCurrentSession().createCriteria(Party.class).list();
			return list;
		}catch(Exception ex){
			throw new Exception();
		}
	}
	
	public List<Tender> getAllTender() throws Exception{
		try{
			List<Tender> list = this.sessionFactory.getCurrentSession().createCriteria(Tender.class).list();
			return list;
		}catch(Exception ex){
			throw new Exception();
		}
	}
	
	public List<Lot> getAllLot(int stateId) throws Exception{
		try{
			List<Lot> list = this.sessionFactory.getCurrentSession().createCriteria(Lot.class)
					.add(Restrictions.eq("state", stateId))
					.addOrder(Order.asc("lot_name"))
					.list();
			return list;
		}catch(Exception ex){
			throw new Exception();
		}
	}
	
	public List<Division> getAllDivision() throws Exception{
		List<Division> list_bid=new ArrayList<Division>();
		try{
			
			String sql = "select division, cast(count(id) as int) as count from main.lot group by division order by division ";			
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
			
			query.setResultTransformer(Transformers.aliasToBean(Division.class));
			list_bid =query.list();
			
		}catch(Exception x){
			System.out.println(x);
			
		}
		return list_bid;
	}
	public boolean checkPartyLotExists(int party_id, int lot_id) throws Exception{
		try {
			String sql = "select id " + "from main.party_lots pl " + "where party_id=:party_id and lot_id=:lot_id";
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
			query.setParameter("party_id", party_id);
			query.setParameter("lot_id", lot_id);
			List result = query.list();
			if (result.size() > 0) {
				return true;
			}
		}catch(Exception x){
			System.out.println(x);
			
		}
		return false;
	}
	
	public List<PartyLots> getPartyLots(int party_id) throws Exception{
		List<PartyLots> list_bid=new ArrayList<PartyLots>();
		try{
			
			String sql = "select pl.id, pl.party_id, pl.lot_id, l.lot_name, l.division, l.lot_given_name "
					+ "from main.party_lots pl "
					+ "inner join main.lot l on l.id =pl.lot_id "
					+ "where pl.party_id=:party_id ";			
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
			query.setParameter("party_id", party_id);
			query.setResultTransformer(Transformers.aliasToBean(PartyLots.class));
			list_bid =query.list();
			
		}catch(Exception x){
			System.out.println(x);
			
		}
		return list_bid;
	}
	
	public List<BidCount> getPartyBidCount(int tender_id) throws Exception{
		List<BidCount> list_bid=new ArrayList<BidCount>();
		try{
			
			String sql = "select party_id, cast(count(id) as int) as bids from main.bid  where tender_id=:tender_id group by party_id";			
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
			query.setParameter("tender_id", tender_id);
			query.setResultTransformer(Transformers.aliasToBean(BidCount.class));
			list_bid =query.list();
			
		}catch(Exception x){
			System.out.println(x);
			
		}
		return list_bid;
	}
	
	public List<LotData> getLotData(int lotId) throws Exception{
		List<LotData> list_bid=new ArrayList<LotData>();
		try{
			
			String sql =  "select t.tender_name,st.party_name, st.rate, st.priority,st.rank, b.tender_id as tender_id, cast(count(party_id) as int) bid_count "+
					" from "+
					" main.bid b "+ 
					" left outer join (select tender_id, rate, priority,rank, party_name "+
					"						from main.bid bb "+
					"						left join main.party p on p.id=bb.party_id "+ 
					"						where lot_id=:lid1 and is_predicted=true "+
					"						) as st on st.tender_id=b.tender_id "+
					" left join main.tender t on t.id=b.tender_id  "+
					" where "+
					" lot_id=:lid2 "+ 
					" group by  t.tender_name, st.party_name,st.rate, st.priority,st.rank, b.tender_id,t.year, t.round "+
					" order by t.year desc , t.round ";
		
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
			query.setParameter("lid1", lotId);
			query.setParameter("lid2", lotId);
			query.setResultTransformer(Transformers.aliasToBean(LotData.class));
			list_bid =query.list();
			
		}catch(Exception x){
			System.out.println(x);
			
		}
		return list_bid;
	}
	
	public Party getParty(int id)throws Exception{
		try{
			Party party = (Party) this.sessionFactory.getCurrentSession().createCriteria(Party.class)
					.add(Restrictions.eq("id", id)).uniqueResult();
			return party;
		}catch(Exception ex){
			throw new Exception();
		}
	}
	
	public Tender getTender(int id)throws Exception{
		try{
			Tender party = (Tender) this.sessionFactory.getCurrentSession().createCriteria(Tender.class)
					.add(Restrictions.eq("id", id)).uniqueResult();
			return party;
		}catch(Exception ex){
			throw new Exception();
		}
	}
	
	public List<LotRate> getLotRate(int lotId)throws Exception{
		try{
			List<LotRate> lotRate = this.sessionFactory.getCurrentSession().createCriteria(LotRate.class)
					.add(Restrictions.eq("list_id", lotId))
					.addOrder(Order.asc("preference")).list();
			return lotRate;
		}catch(Exception ex){
			throw new Exception();
		}
	}
	
	public List<LotList> getLotList() throws Exception{
		try{
		List<LotList> list = this.sessionFactory.getCurrentSession().createCriteria(LotList.class).list();
		return list;
		}catch(Exception ex){
			throw new Exception();
		}
	}
	public List<Lot> getAllLot() throws Exception{
		try{
		List<Lot> list = this.sessionFactory.getCurrentSession().createCriteria(Lot.class).list();
		return list;
		}catch(Exception ex){
			throw new Exception();
		}
	}
	
	

	public int checkPartyExist(Party entity) throws Exception {

		try {
			
			List<Party> list = (List<Party>)this.sessionFactory.getCurrentSession()
			.createCriteria(entity.getClass())
			.add(Restrictions.eq("bidder_id", entity.getBidder_id()))
			.list();

			if (list!=null && list.size()>0){
				Party party = list.get(0);
				
				return party.getId();
			}
			return -1;
		} catch (Exception e) {
			throw new Exception();
		}
		
	}
	
	public int checkPartyExist_name(Party entity) throws Exception {

		try {
			
			List<Party> list = (List<Party>)this.sessionFactory.getCurrentSession()
			.createCriteria(entity.getClass())
			.add(Restrictions.eq("party_name", entity.getParty_name()))
			.list();

			if (list!=null && list.size()>0){
				Party party = list.get(0);
				
				return party.getId();
			}
			return -1;
		} catch (Exception e) {
			throw new Exception();
		}
		
	}
	public Party loadUserByName(String pname) throws Exception {

		try {
			
			List<Party> list = (List<Party>)this.sessionFactory.getCurrentSession()
			.createCriteria(Party.class)
			.add(Restrictions.eq("email", pname))
			.list();

			if (list!=null && list.size()>0){
				Party party = list.get(0);
				System.out.println("found party....");
				return party;
			}
			return null;
		} catch (Exception e) {
			System.out.println("Exception in loadUserBy Name" + e);
			throw new Exception();
		}
		
	}
	
	public Party validateUser(Login login) throws Exception{
		try {
			
			List<Party> list = (List<Party>)this.sessionFactory.getCurrentSession()
			.createCriteria(Party.class)
			.add(Restrictions.eq("email", login.getUsername()))
			.add(Restrictions.eq("password", login.getPassword()))
			.list();

			if (list!=null && list.size()>0){
				
				Party party = list.get(0);
				return party;
				
			}
			return null;
		} catch (Exception e) {
			throw new Exception();
		}
	}
	
	public boolean deleteBidByTender(Tender tender){
		try{
			String sql = "delete * FROM main.bid " + "where tender_id=" + tender.getId();
	
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
			query.executeUpdate();
			
		}catch(Exception x){
			return false;
		}
		return true;
	}
	
	
	public boolean deleteLotData(int lotId){
		try{
			String sql = "delete FROM main.lot_data " + "where lot_id=" + lotId;
	
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
			query.executeUpdate();
			
		}catch(Exception x){
			return false;
		}
		return true;
	}
	
	
	public boolean deleteLotDataByList(int listId){
		try{
			String sql = "delete FROM main.lot_data " + "where list_id=" + listId;
	
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
			query.executeUpdate();
			
		}catch(Exception x){
			return false;
		}
		return true;
	}
	
	public int getBidCount(Tender entity,Party party) throws Exception {

		try {

			Criteria c =  this.sessionFactory.getCurrentSession()
			.createCriteria(Bid.class)
			.setProjection(Projections.rowCount())
			.add(Restrictions.eq("tender_id", entity.getId()));
			if (party!=null){
				c.add((Restrictions.eq("party_id", party.getId())));
			}
			int count = ((Long)c.uniqueResult()).intValue();

			return count;
		} catch (Exception e) {
			throw new Exception();
		}
	}
	
	public List<Financials> getPartyFinancialByTender(Tender tender) throws Exception {
		List<Financials> list_financials = new ArrayList<Financials>();
		
		try{
			Criteria c =  this.sessionFactory.getCurrentSession()
					.createCriteria(Financials.class)
					.add(Restrictions.eq("tender_id", tender.getId()));
			list_financials = c.list();
		}catch(Exception ex){
			
		}
		return list_financials;
		
	}
	public boolean updateBidRankByTender(Tender tender, boolean resetCancel) throws Exception {
		List<Bid> list_bid=new ArrayList<Bid>();;
		try{
			String sql2 = "update main.bid "
					+ "set is_predicted=false, rank=null, is_insufficient=null "
					+ ", is_canceled=false "
					+ "where tender_id=:tender_id ";
			if(!resetCancel){
				sql2 = "update main.bid "
						+ "set is_predicted=false, rank=null, is_insufficient=null "
						+ "  "
						+ "where tender_id=:tender_id ";
			}
			
			String sql = "update main.bid "+ 
					"set rank=rnk "+
					"from (select b.*, dense_rank() OVER (PARTITION BY lot_id ORDER BY rate desc, priority asc) AS rnk "+ 
					"from main.bid as b "+
					"left outer join main.lot as l on b.lot_id=l.id "+
					"where tender_id=:tenderId2 "+
					"order by lot_name asc, rate desc, priority asc) as a "+
					"where bid.id=a.id and bid.tender_id=:tenderId1";
			
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql2);
			query.setParameter("tender_id", tender.getId());
			query.executeUpdate();
			
			SQLQuery query1 = (SQLQuery) session.createSQLQuery(sql);
			query1.setParameter("tenderId1", tender.getId());
			query1.setParameter("tenderId2", tender.getId());
			query1.executeUpdate();

		}catch(Exception x){
			return false;
		}
		return true;
	}
	
	public List<ResultBid> getLotDetailsTenderWise(int lotId, int tenderId) throws Exception {
		List<ResultBid> list_bid=new ArrayList<ResultBid>();;
		try{
			String sql = "select b.id,lot_id,party_id,rank,p.party_name,b.rate,b.priority,b.amount "+
						"from main.bid b "+
						"left join main.party p on b.party_id=p.id "+
						"left join main.lot l on b.lot_id=l.id "+
						"where lot_id=:lot_id and tender_id=:tender_id  "+
						"order by rank, priority, lot_name  ";
			
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
			query.setParameter("tender_id", tenderId);
			query.setParameter("lot_id", lotId);
			//query.addEntity(ResultBid.class);
			query.setResultTransformer(Transformers.aliasToBean(ResultBid.class));
			list_bid = query.list();
			
		}catch(Exception x){
			System.out.println(x);
			
		}
		return list_bid;
	}
	
	public void updateTenderResult(Integer[] bid_ids, int  year, int state) throws Exception {
		try{
			String sql = "update main.bid set is_selected=false where tender_id in (select id from main.tender where year= :year ";
			String sql1 = "update main.bid set is_selected=true where id in(:bid_ids) ";
			
			Session session = sessionFactory.getCurrentSession();
			
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
			query.setParameter("year", year);
			query.executeUpdate();
			
			SQLQuery query1 = (SQLQuery) session.createSQLQuery(sql1);
			query1.setParameterList("bid_ids", bid_ids);
			query1.executeUpdate();
			
		}catch(Exception x){
			System.out.println(x);
			
		}
	}
	public Map<String,Integer> getMinMaxAvgRate(int tender_id){
		try{
			String sql = "select cast(min(rate) as int) as minRate, cast(max(rate) as int) as maxRate, cast(avg(rate) as int) as avgRate, count(id) as lotCount "+
						"from main.bid "+
						"where  is_predicted=true "+
						"and tender_id=:tender_id ";
			
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
			query.setParameter("tender_id", tender_id);
			//query.addEntity(ResultBid.class);
			
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Integer>> list = (List<Map<String,Integer>>) query.list();
			
			return list.get(0);
		}catch(Exception x){
			System.out.println(x);
		}
		return null;
	}
	
	public Map<String,Integer> getLotMinMaxAvgRate(int lot_id){
		try{
			String sql = "select cast(min(rate) as int) as minRate, cast(max(rate) as int) as maxRate, cast(avg(rate) as int) as avgRate, count(id) as lotCount "+
						"from main.bid "+
						"where  is_predicted=true "+
						"and lot_id=:lot_id ";
			
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
			query.setParameter("lot_id", lot_id);
			//query.addEntity(ResultBid.class);
			
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Integer>> list = (List<Map<String,Integer>>) query.list();
			
			return list.get(0);
		}catch(Exception x){
			System.out.println(x);
		}
		return null;
	}
	
	public List<Map<String,Integer>> getYearWiseMinMaxAvgRate(int state_id){
		try{
			String sql = "select year, round, cast(min(rate) as int) as min_rate, cast(max(rate)as int) as max_rate ,cast(avg(rate) as int) as avg_rate "+
						"from main.bid b "+
						"inner join main.tender t on t.id=b.tender_id "+
						"where is_predicted=true "+
						"and state=:state and t.status=true "+
						"group by tender_id,year, round "+
						"order by year desc, round ";
			
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
			query.setParameter("state", state_id);
			//query.addEntity(ResultBid.class);
			
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Integer>> list = (List<Map<String,Integer>>) query.list();
			
			return list;
		}catch(Exception x){
			System.out.println(x);
		}
		return null;
	}
	
	public List<Map<String,Double>> getYearWiseAmount(int state_id){
		try{
			String sql = "select year,round, round(cast(sum(amount)/10000000 as decimal),2) amount "+
						"from main.bid b "+
						"inner join main.tender t on t.id=b.tender_id "+
						"where   "+
						"is_predicted=true and t.status=true and state=:state "+
						"group by tender_id, tender_name, year, round "+
						"order by year desc, round  ";
			
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
			query.setParameter("state", state_id);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Double>> list = (List<Map<String,Double>>) query.list();
			
			return list;
		}catch(Exception x){
			System.out.println(x);
		}
		return null;
	}
	
	
	
	public List<Map<String,Double>> getTop5Bidder(int tender_id){
		try{
			String sql = "select p.party_name, sum(amount) amt "+
					"from main.bid b "+
					"inner join main.party p on p.id=b.party_id "+
					"where   "+
					"tender_id=:tender_id  "+
					"group by party_id,party_name "+
					"order by amt desc "+
					"limit 5  "; 
			
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
			query.setParameter("tender_id", tender_id);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Double>> list = (List<Map<String,Double>>) query.list();
			
			return list;
		}catch(Exception x){
			System.out.println(x);
		}
		return null;
	}
	
	public List<Map<String,Double>> getTop5Lot(int tender_id){
		try{
			String sql = "select lot_name,lot_given_name,division, max(rate) as rate "+
						"from main.bid b "+
						"inner join main.lot l on l.id=b.lot_id "+
						"where tender_id=:tender_id  "+
						"group by lot_id,lot_name,lot_given_name,division "+
						"order by rate desc "+
						"limit 5 "; 
			
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
			query.setParameter("tender_id", tender_id);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Double>> list = (List<Map<String,Double>>) query.list();
			
			return list;
		}catch(Exception x){
			System.out.println(x);
		}
		return null;
	}
	
	public List<Map<String,Object>> getPartyBids(int tender_id,int party_id){
		try{
			String sql = "select lot_name,lot_given_name,division, rate "+
						"from main.bid b "+
						"inner join main.lot l on l.id=b.lot_id "+
						"where tender_id=:tender_id and party_id = :party_id "+
						"order by lot_name  ";
			
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
			query.setParameter("tender_id", tender_id);
			query.setParameter("party_id", party_id);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Object>> list = (List<Map<String,Object>>) query.list();
			
			return list;
		}catch(Exception x){
			System.out.println(x);
		}
		return null;
	}
	
	public List<Map<String,Double>> getDivisionWiseRates(int tender_id){
		try{
			String sql = "select division, cast(min(rate) as int) as min_rate, cast(max(rate) as int) as max_rate,cast (avg(rate) as int) as avg_rate "+
						"from main.bid b "+
						"inner join main.lot l on l.id=b.lot_id "+
						"where  is_predicted=true and tender_id=:tender_id "+
						"group by division "+
						"order by avg_rate desc limit 10"; 
			
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
			query.setParameter("tender_id", tender_id);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Double>> list = (List<Map<String,Double>>) query.list();
			
			return list;
		}catch(Exception x){
			System.out.println(x);
		}
		return null;
	}
	
	public List<Map<String,Integer>> getLotYearWiseMinMaxAvgRate(int lot_id){
		try{
			String sql = "select year, round, cast(min(rate) as int) as min_rate, cast(max(rate)as int) as max_rate ,cast(avg(rate) as int) as avg_rate "+
						"from main.bid b "+
						"inner join main.tender t on t.id=b.tender_id "+
						"where  is_predicted=true "+
						"and lot_id=:lot_id "+
						"group by tender_id,year, round "+
						"order by year desc, round ";
			
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
			query.setParameter("lot_id", lot_id);
			//query.addEntity(ResultBid.class);
			
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Integer>> list = (List<Map<String,Integer>>) query.list();
			
			return list;
		}catch(Exception x){
			System.out.println(x);
		}
		return null;
	}
	
	
	
	public List<Map<String,Integer>> getLotYearWiseCount(int lot_id){
		try{
			String sql = "select year, round, count(b.id) as avg_rate "+
						"from main.bid b "+
						"inner join main.tender t on t.id=b.tender_id "+
						"where   "+
						" lot_id=:lot_id "+
						"group by lot_id,year, round "+
						"order by year desc, round ";
			
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
			query.setParameter("lot_id", lot_id);
			
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Integer>> list = (List<Map<String,Integer>>) query.list();
			
			return list;
		}catch(Exception x){
			System.out.println(x);
		}
		return null;
	}
	
	public List<Map<String,Integer>> getLotCountRangeWise(int tender_id){
		try{
			String sql = "select cast ((cast ((rate/500) as int)*500) as text) ||'-'|| cast(((cast ((rate/500) as int)+1) * 500) as text) amt_range, count(id) lot_count from  "+ 
						"main.bid b "+
						"where tender_id=:tender_id and is_predicted=true "+
						"group by cast ((rate/500) as int) "+
						"order by amt_range "; 
			
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
			query.setParameter("tender_id", tender_id);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Integer>> list = (List<Map<String,Integer>>) query.list();
			
			return list;
		}catch(Exception x){
			System.out.println(x);
		}
		return null;
	}
	
	public List<Map<String,String>> getTenderList(int state){
		try{
			String sql = "select id, (year ||' - Round '|| round) as name, tender_name as tname from "+ 
						"main.tender "+
						"where state=:state and status=true "+
						"order by year desc, round "; 
			
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
			query.setParameter("state", state);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,String>> list = (List<Map<String,String>>) query.list();
			
			return list;
		}catch(Exception x){
			System.out.println(x);
		}
		return null;
	}
	
	public List<Map<String,String>> getPartyByTender(int tender_id){
		try{
			String sql = "select distinct p.id, party_name as name from "+ 
						"main.party p "+
						"inner join main.bid b on p.id=b.party_id "+
						"where b.tender_id=:tender_id "+
						"order by name "; 
			
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
			query.setParameter("tender_id", tender_id);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,String>> list = (List<Map<String,String>>) query.list();
			
			return list;
		}catch(Exception x){
			System.out.println(x);
		}
		return null;
	}
	
	public List<ResultBid> getBidByTenderOrderRankPriority(Tender tender,boolean is_canceled, double rate, double capingRate) throws Exception {
		List<ResultBid> list_bid=new ArrayList<ResultBid>();;
		try{
			String sql = "select b.id,b.lot_id,lot_name,party_id,rank,p.party_name,b.rate,b.priority,b.amount,b.is_selected "+
						"from main.bid b "+
						"left join main.party p on b.party_id=p.id "+
						"left join main.lot l on b.lot_id=l.id "+
						"left outer join (select lot_id, ((rate * :capingRate )/100) as rate from main.bid where tender_id=:tender_id and rank=1 order by lot_id) as caping on b.lot_id=caping.lot_id "+

						"where tender_id=:tender_id and is_canceled=:is_canceled "
						+" and b.rate > :rate and b.rate>=caping.rate "+
						"order by rank, priority, rate desc ";
			
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
			query.setParameter("tender_id", tender.getId());
			query.setParameter("capingRate", capingRate);
			
			//query.addEntity(ResultBid.class);
			query.setParameter("is_canceled", is_canceled);
			query.setParameter("rate", rate);
			query.setResultTransformer(Transformers.aliasToBean(ResultBid.class));
			list_bid = query.list();
			
		}catch(Exception x){
			System.out.println(x);
			
		}
		return list_bid;
	}
	
	public List<ResultBid> getBidByTenderOrderRankRate(Tender tender,boolean is_canceled, double rate) throws Exception {
		List<ResultBid> list_bid=new ArrayList<ResultBid>();;
		try{
			String sql = "select b.id,lot_id,lot_name,party_id,rank,p.party_name,b.rate,b.priority,b.amount,b.is_selected "+
						"from main.bid b "+
						"left join main.party p on b.party_id=p.id "+
						"left join main.lot l on b.lot_id=l.id "+
						"where tender_id=:tender_id  and is_canceled=:is_canceled and b.rate > :rate "+
						"order by rank, rate desc, priority, lot_name ";
			
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
			query.setParameter("tender_id", tender.getId());
			query.setParameter("is_canceled", is_canceled);
			query.setParameter("rate", rate);
			//query.addEntity(ResultBid.class);
			query.setResultTransformer(Transformers.aliasToBean(ResultBid.class));
			list_bid = query.list();
			
		}catch(Exception x){
			System.out.println(x);
			
		}
		return list_bid;
	}
	
	
	public List<ResultBid> getBidByTender(Tender tender) throws Exception {
		List<ResultBid> list_bid=new ArrayList<ResultBid>();;
		try{
			String sql = "select b.id,lot_id,lot_name,party_id,rank,p.party_name,b.rate,b.priority,b.amount,b.is_selected "+
						"from main.bid b "+
						"left join main.party p on b.party_id=p.id "+
						"left join main.lot l on b.lot_id=l.id "+
						"where tender_id=:tender_id  "+
						"order by cast(lot_name as int) ,is_predicted, rank ";
			
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
			query.setParameter("tender_id", tender.getId());
			//query.addEntity(ResultBid.class);
			query.setResultTransformer(Transformers.aliasToBean(ResultBid.class));
			list_bid = query.list();
			
		}catch(Exception x){
			System.out.println(x);
			
		}
		return list_bid;
	}
	
	public List<Map<String,Object>> getBidByTenderYear(int year,int state) throws Exception {
		List<ResultBid> list_bid=new ArrayList<ResultBid>();;
		try{
			String sql = "select b.id,lot_id,lot_name,lot_given_name,round, party_id,rank,p.party_name,b.rate,b.priority,b.amount,b.is_selected,b.is_predicted "+
						"from main.tender t "+
						"left join main.bid b on b.tender_id=t.id "+ 
						"left join main.party p on b.party_id=p.id  "+
						"left join main.lot l on b.lot_id=l.id  "+
						"where year=:year and t.state=:state "+
						"order by cast(lot_name as int), round, party_name  ";
			if (state==2){
				sql = "select b.id,lot_id,lot_name,lot_given_name,round, party_id,rank,p.party_name,b.rate,b.priority,b.amount,b.is_selected,b.is_predicted "+
						"from main.tender t "+
						"left join main.bid b on b.tender_id=t.id "+ 
						"left join main.party p on b.party_id=p.id  "+
						"left join main.lot l on b.lot_id=l.id  "+
						"where year=:year and t.state=:state "+
						"order by lot_name, round, party_name  ";
			}
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
			query.setParameter("year", year);
			query.setParameter("state", state);

			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Object>> list = (List<Map<String,Object>>) query.list();
			return list;
		}catch(Exception x){
			System.out.println(x);
			
		}
		return null;
	}
	
	public List<Map<String,Object>> getLotRatesYearWise(int state) throws Exception {
		List<ResultBid> list_bid=new ArrayList<ResultBid>();;
		try{
			String sql = "select l.lot_name,l.lot_given_name,b.lot_id, l.division, t.year, t.round,p.party_name, b.rate "+
							"from main.bid b "+
							"inner join main.tender t on b.tender_id=t.id and t.state=:state "+
							"inner join main.party p on p.id=b.party_id "+
							"inner join main.lot l on l.id=b.lot_id "+
							"inner join  "+
							"(select b.lot_id, t.year, max(round) as round "+
							"from main.bid b "+
							"inner join main.tender t on t.id=b.tender_id "+
							"where state=:state "+
							"group by t.year, lot_id) as allotment on b.lot_id=allotment.lot_id and t.year=allotment.year and t.round= allotment.round "+
							"where is_predicted=true "+
							"order by l.lot_given_name,t.year,round  ";
			
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
			
			query.setParameter("state", state);

			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Object>> list = (List<Map<String,Object>>) query.list();
			return list;
		}catch(Exception x){
			System.out.println(x);
			
		}
		return null;
	}
	
	public List<ResultBid> getBidByTender(Tender tender,boolean is_canceled) throws Exception {
		List<ResultBid> list_bid=new ArrayList<ResultBid>();;
		try{
			String sql = "select b.id,lot_id,lot_name,party_id,rank,p.party_name,b.rate,b.priority,b.amount "+
						"from main.bid b "+
						"left join main.party p on b.party_id=p.id "+
						"left join main.lot l on b.lot_id=l.id "+
						"where tender_id=:tender_id and is_canceled=:is_canceled "+
						"order by rank, priority, lot_name  ";
			
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
			query.setParameter("tender_id", tender.getId());
			query.setBoolean("is_canceled", is_canceled);
			//query.addEntity(ResultBid.class);
			query.setResultTransformer(Transformers.aliasToBean(ResultBid.class));
			list_bid = query.list();
			
		}catch(Exception x){
			System.out.println(x);
			
		}
		return list_bid;
	}
	
	public List<ResultBid> getBidByTender_test(Tender tender) throws Exception {
		List<ResultBid> list_bid=new ArrayList<ResultBid>();;
		try{
			String sql = "select b.id,b.lot_id,party_id,rank,p.party_name,b.rate,b.priority,b.amount "+
						 
					" from main.bid b  "+
					" inner join main.lot l  on l.id=b.lot_id "+
					" inner join main.party p on p.id = b.party_id  "+
					" left outer join main.tmp_mp_collection c on c.lot_no = l.lot_name "+
					" left outer join (select lot_id, max(rate) as mx_rate from main.bid where tender_id=:tender_id1 group by lot_id ) as mx on mx.lot_id=b.lot_id "+
					" where b.tender_id=:tender_id2 "+ 
					" and is_canceled=false "+
					" order by rank, "+
					//" (case "+ 
					//" when mx_rate >((cast(r2016 as int) + cast(r2017 as int) + cast(r2018 as int))/3)  then 1 "+
					//" else 2  "+
					//" End) ,"+
					 "   priority ";
			
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
			query.setParameter("tender_id1", tender.getId());
			query.setParameter("tender_id2", tender.getId());
			//query.addEntity(ResultBid.class);
			query.setResultTransformer(Transformers.aliasToBean(ResultBid.class));
			list_bid = query.list();
			
		}catch(Exception x){
			System.out.println(x);
			
		}
		return list_bid;
	}
	
	public List<ResultBid> getBidByTenderByParty(Tender tender) throws Exception {
		List<ResultBid> list_bid=new ArrayList<ResultBid>();;
		try{
			String sql = "select b.id,lot_id,party_id,rank,p.party_name,b.rate,b.priority,b.amount "+
						"from main.bid b "+
						"left join main.party p on b.party_id=p.id "+
						"left join main.lot l on b.lot_id=l.id "+
						"where tender_id=:tender_id and is_canceled=false "+
						"order by rank, party_name, amount desc, priority, lot_name  ";
			
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
			query.setParameter("tender_id", tender.getId());
			//query.addEntity(ResultBid.class);
			query.setResultTransformer(Transformers.aliasToBean(ResultBid.class));
			list_bid = query.list();
			
		}catch(Exception x){
			System.out.println(x);
			
		}
		return list_bid;
	}
	
	public List<ReportBean> getReportByParty(int stateNo,Integer[] party_ids, Integer[] tender_ids) throws Exception {
		List<ReportBean> list_bid=new ArrayList<ReportBean>();;
		try{
			
			String sql = "select p.party_name,t.tender_name,l.lot_given_name, l.lot_name,b.rank, b.priority, b.rate,b.qty,b.amount,b.is_predicted,b.is_canceled,p.id party_id,l.id lot_id,t.id tender_id, "+
					"cast(t.rnk as int) "+
					"from main.bid b  "+
					"inner join main.party p on b.party_id=p.id "+ 
					"inner join (select t1.*, dense_rank() OVER (ORDER BY  year desc, round asc ) AS rnk "+ 
					"from main.tender t1 where state=:varState and year>2017 and (id in (:varTenderIds) or :varTenderCondition) ) t on b.tender_id = t.id  "+
					"inner join main.lot l on b.lot_id = l.id and t.state=l.state  "+
					"where (p.id in (:varPartyIds) or :varPartyCondition) "+
					"order by party_name,lot_name,tender_name  ";
			
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
			query.setParameter("varState", stateNo);
			
			if (tender_ids!=null && tender_ids.length>0){
				query.setBoolean("varTenderCondition", false);
				query.setParameterList("varTenderIds", tender_ids);
			}else{
				query.setParameterList("varTenderIds",  new Integer[]{-1});
				query.setBoolean("varTenderCondition", true);
			}
			
			if (party_ids!=null && party_ids.length>0){
				query.setBoolean("varPartyCondition", false);
				query.setParameterList("varPartyIds", party_ids);
			}else{
				query.setParameterList("varPartyIds", new Integer[]{-1});
				query.setBoolean("varPartyCondition", true);
			}
			query.setResultTransformer(Transformers.aliasToBean(ReportBean.class));
			list_bid = query.list();
			
		}catch(Exception x){
			System.out.println(x);
			
		}
		return list_bid;
	}
	
	public List<ReportBean> getTenderResult(int tenderId) throws Exception {
		List<ReportBean> list_bid=new ArrayList<ReportBean>();;
		try{
			
			String sql = "select p.party_name,t.tender_name,l.lot_given_name, l.lot_name, l.division,b.rate,b.qty,b.amount,b.is_predicted,"
					+ "b.is_canceled,p.id party_id,l.id lot_id,t.id tender_id,cast(t.rnk as int),b.rank as rank, "
					+ "b.priority as priority, cap.purchase_capacity, cap.emd, cap.bid_capacity, cap.consumed_capacity "+
					"from main.bid b  "+
					"left join main.party p on b.party_id=p.id "+
					"left join main.lot l on b.lot_id = l.id "+
					"left join (select t1.*, dense_rank() OVER (ORDER BY  tender_name) AS rnk from main.tender t1) t on b.tender_id = t.id "
					+ " left join (select   b1.party_id, sum(amount) consumed_capacity, purchase_capacity,emd, bid_capacity  "
					+ " from main.bid b1 "
					+ " inner join  main.financial f on b1.tender_id=f.tender_id and b1.party_id=f.party_id "
					+ " where b1.tender_id=:tid1 and  "
					+ " is_predicted=true "
					+ " group by b1.party_id,purchase_capacity,emd, bid_capacity) as cap on cap.party_id=b.party_id "+
					"where b.tender_id=:tid and b.is_predicted=true "+
					"order by party_name,rank, priority,tender_name ";

			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
			query.setParameter("tid", tenderId);
			query.setParameter("tid1", tenderId);
			query.setResultTransformer(Transformers.aliasToBean(ReportBean.class));
			list_bid = query.list();

		}catch(Exception x){
			System.out.println(x);
			
		}
		return list_bid;
	}
	
	public List<Map<String,Object>> getTenderResultDisplay(int tenderId) throws Exception {
		try{
			
			String sql = "select p.party_name,t.tender_name,l.lot_given_name, l.lot_name, l.division,b.rate,b.qty,b.amount,b.is_predicted,b.is_canceled,p.id party_id,l.id lot_id,t.id tender_id,b.rank as rank, b.priority as priority "+
					"from main.bid b  "+
					"inner join main.party p on b.party_id=p.id "+
					"inner join main.lot l on b.lot_id = l.id "+
					"inner join main.tender t on t.id=b.tender_id "+
					"where b.tender_id=:tender_id and b.is_selected=true "+
					"order by lot_name ";

			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
			query.setParameter("tender_id", tenderId);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Object>> list_bid = query.list();
			return list_bid;
		}catch(Exception x){
			System.out.println(x);
			
		}
		return null;
	}
	
	public List<Map<String,Object>> getYearList() throws Exception {
		try{
			
			String sql = "select distinct year as year from main.tender  ";

			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Object>> list_bid = query.list();
			return list_bid;
		}catch(Exception x){
			System.out.println(x);
			
		}
		return null;
	}
	
	
	
	public List<ReportBean> getReportByLot(String[] ids, int state) throws Exception {
		List<ReportBean> list_bid=new ArrayList<ReportBean>();;
		try{
			String sql = "select p.party_name,t.tender_name,l.division, l.lot_given_name, split_part( l.lot_name,'/',2) as lot_name,b.rate,b.qty,b.amount,b.is_predicted,b.is_canceled,p.id party_id,l.id lot_id,t.id tender_id,cast(t.rnk as int),b.priority, b.rank, f.purchase_capacity as capacity "+
					"from main.bid b  "+
					"left join main.party p on b.party_id=p.id "+
					"left join main.lot l on b.lot_id = l.id and l.state=:state1 "+
					"left join main.financial f on f.party_id=b.party_id and f.tender_id=b.tender_id "+
					"inner join (" 
					+ "select t1.*, dense_rank() OVER (ORDER BY  year desc, round ) AS rnk from main.tender t1 "
					+ "where state=:state3  "
					+ ") t on b.tender_id = t.id   "
					+ " where t.state=:state2   "
					//+"and split_part( l.lot_name,'/',2) in ('1921','1870','1942','248','164','519' "
					/*+ "'490','491','493','494','496',"
					+ "'497','498','499','519','530',"
					+ "'536','556','557','558','731',"*/
					//+ " ) "
					//	+"and  l.lot_name in ('549','548','534','537','540','553','535','539','545') "
					//+"and  l.lot_name in ('469','479','480','481') "
					+" and b.tender_id in (60,72,74)  " 
					//+ " and year=2020 "
					//+"and  l.lot_name in ('289','292','295','296','362','361','359','316','314','315','317','319','299','262','293','284','298','312','309','297') "

//					+"and  l.id in (:ids) "
					 //+"and  b.tender_id=55 "
					//+"and l.division in ('EAST SIDHI','E.SIDHI','W.SIDHI''UMARIYA','UMARIA','S.SHAHDOL','N.SHAHDOL','REEVA','REWA') "//and b.tender_id=41 "+
					+"order by lot_name, rank, party_name, tender_name ";
			//String sql = "select cast('' as character varying) as party_name,cast('1' as int) party_id, t.tender_name,l.division, l.lot_given_name, l.lot_name,b.rate,b.qty,b.amount,b.is_predicted,b.is_canceled, l.id lot_id,t.id tender_id,cast(t.rnk as int),b.priority, b.rank from main.bid b  left join main.lot l on b.lot_id = l.id left join (select t1.*, dense_rank() OVER (ORDER BY  tender_name) AS rnk from main.tender t1) t on b.tender_id = t.id where is_predicted=true order by lot_name, tender_name ";
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
	//		query.setParameterList("ids", ids);
			query.setParameter("state1", 1);
			query.setParameter("state2", 1);
			query.setParameter("state3", 1);
			
			query.setResultTransformer(Transformers.aliasToBean(ReportBean.class));
			list_bid = query.list();
		}catch(Exception x){
			System.out.println(x);
		}
		return list_bid;
	}
	
	public List<ReportBean> getReportByLot(String[] ids, int state, int yearCount) throws Exception {
		List<ReportBean> list_bid=new ArrayList<ReportBean>();;
		try{
			String sql = "select p.party_name,t.tender_name,l.division, l.lot_given_name, l.lot_name,b.rate,b.qty,b.amount,b.is_predicted,b.is_canceled,p.id party_id,l.id lot_id,t.id tender_id,cast(t.rnk as int),b.priority, b.rank, f.purchase_capacity as capacity "+
					"from main.bid b  "+
					"left join main.party p on b.party_id=p.id "+
					"left join main.lot l on b.lot_id = l.id and l.state=:state1 "+
					"left join main.financial f on f.party_id=b.party_id and f.tender_id=b.tender_id "+
					"inner join ("
					+ "select t1.*, dense_rank() OVER (ORDER BY  year desc, round ) AS rnk "
					+ "from main.tender t1 "
					+ "where status=true and state=:state2 "
					+ "and year in (select distinct year "
					+ "	from main.tender "
					+ "	where state=:state4 "
					+ "	order by year desc "
					+ "	limit :yearCount )"
					+ "order by year desc ,round asc "
					+ ") t on b.tender_id = t.id  "
					+ " where t.state=:state3 and t.status=true "
					//+"and  l.lot_name in ('277','283','364') "
					//+" and b.tender_id in (47,48)  "
					//+ " and b.tender_id=47 "
					//+"and  b.tender_id=54 "
					//+"and  l.id in (:ids) "
					//+"and l.division in () "//and b.tender_id=41 "+
					+"order by lot_name, rank, party_name,tender_name ";
			//String sql = "select cast('' as character varying) as party_name,cast('1' as int) party_id, t.tender_name,l.division, l.lot_given_name, l.lot_name,b.rate,b.qty,b.amount,b.is_predicted,b.is_canceled, l.id lot_id,t.id tender_id,cast(t.rnk as int),b.priority, b.rank from main.bid b  left join main.lot l on b.lot_id = l.id left join (select t1.*, dense_rank() OVER (ORDER BY  tender_name) AS rnk from main.tender t1) t on b.tender_id = t.id where is_predicted=true order by lot_name, tender_name ";
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
			Integer[] array = new Integer[ids.length];
			int i=0;
			for (String cur: ids){
				array[i++] = Integer.parseInt(cur);
			}

			//query.setParameterList("ids", array);
			query.setParameter("state1", state);
			query.setParameter("state2", state);
			query.setParameter("state3", state);
			query.setParameter("state4", state);
			query.setParameter("yearCount", yearCount);
			query.setResultTransformer(Transformers.aliasToBean(ReportBean.class));
			list_bid = query.list();
		}catch(Exception x){
			System.out.println(x);
		}
		return list_bid;
	}
	
	
	public boolean updateBidStatus(Bid bid){
		try{
			String sql = "update main.bid set is_predicted=true where id=:bid";
	
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
			query.setParameter("bid", bid.getId());
			query.executeUpdate();
			
		}catch(Exception x){
			return false;
		}
		return true;
	}
	
	public void updateResultByYearRate(int year, String lot_name, int rate, int state, String party_name ){
		try{
			String updatesql = "update main.bid "
					+ "set is_selected=true where id =:bid ";
			
			String selectsql = "select b.id from main.bid b "
					+ "inner join main.tender t on t.id=b.tender_id "
					+ "inner join main.lot l on l.id= b.lot_id "
					+ "inner join main.party p on p.id= b.party_id "
					+ "where year=:year and lot_name=:lot_name and rate =:rate and t.state=:state and party_name=:party_name order by round desc ";
	
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(selectsql);
			query.setParameter("year", year);
			query.setParameter("lot_name", lot_name);
			query.setParameter("rate", rate);
			query.setParameter("state", state);
			query.setParameter("party_name", party_name);
			List result = query.list() ;
			if (result.size()>0){
				//Object[] colmns = (Object[])result.get(0);
				Integer id = (Integer)result.get(0);
				SQLQuery updateQuery = (SQLQuery) session.createSQLQuery(updatesql);
				updateQuery.setParameter("bid", id);
				updateQuery.executeUpdate();
				System.out.println(String.format("result updated for :%d, %d, %d, %s, %d",result.size(), id, year, lot_name, rate));
			}else{
				System.out.println(String.format("multiple entries found for :%d, %d, %s, %d",result.size(), year, lot_name, rate));
			}
			
		}catch(Exception x){
			System.out.println(x);
		}
	}
	
	public void updateLotDetails( String lot_name, String lot_given_name,String division, int state ){
		try{
			String updatesql = "update main.lot "
					+ "set lot_given_name=:lot_given_name, division=:division where lot_name =:lot_name and state=:state ";
			
	
			Session session = sessionFactory.getCurrentSession();
			SQLQuery updateQuery = (SQLQuery) session.createSQLQuery(updatesql);
			updateQuery.setParameter("lot_given_name", lot_given_name);
			updateQuery.setParameter("division", division);
			updateQuery.setParameter("lot_name", lot_name);
			updateQuery.setParameter("state", state);
			updateQuery.executeUpdate();
			
		}catch(Exception x){
			System.out.println(x);
		}
	}
	
	
	public boolean updateBidInSufficient(Bid bid){
		try{
			String sql = "update main.bid set is_insufficient=true where id=:bid";
	
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
			query.setParameter("bid", bid.getId());
			query.executeUpdate();
			
		}catch(Exception x){
			return false;
		}
		return true;
	}

	
	public int checkTenderExist(Tender entity) throws Exception {

		try {
			
			List<Tender> list = (List<Tender>)this.sessionFactory.getCurrentSession()
			.createCriteria(entity.getClass())
			.add(Restrictions.eq("tender_name", entity.getTender_name()))
			.add(Restrictions.eq("state", entity.getState()))
			.add(Restrictions.ne("id", entity.getId())).list();

			if (list!=null && list.size()>0){
				Tender party = list.get(0);
				return party.getId();
			}
			return -1;
		} catch (Exception e) {
			throw new Exception();
		}
		
	}
	
	public int checkLotExist(Lot entity) throws Exception {

		try {
			
			List<Lot> list = (List<Lot>)this.sessionFactory.getCurrentSession()
			.createCriteria(entity.getClass())
			.add(Restrictions.eq("lot_name", entity.getLot_name()))
			.add(Restrictions.eq("state", entity.getState()))
			.add(Restrictions.ne("id", entity.getId())).list();

			if (list!=null && list.size()>0){
				Lot lot = list.get(0);
				return lot.getId();
			}
			return -1;
		} catch (Exception e) {
			throw new Exception();
		}
		
	}
	
	public int saveLotList(LotList entity) throws Exception {

		try {
			
			List<LotList> list = (List<LotList>)this.sessionFactory.getCurrentSession()
			.createCriteria(entity.getClass())
			.add(Restrictions.eq("name", entity.getName())).list();
			
			if (list!=null && list.size()>0){
				return 0;
			}else{
				return saveEntity(entity);
			}
		} catch (Exception e) {
			throw new Exception();
		}
		
	}
	
	

	public boolean updateEntity(Object entity) throws Exception {

		try {
			this.sessionFactory.getCurrentSession().update(entity);
			return true;
		} catch (Exception e) {
			throw new Exception();
		}
	}
	

}

package mp.procurement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import mp.procurement.model.TradeRecord;
import mp.procurement.model.UserActivity;

@Transactional
@Service
public class CommonService {

	@Autowired
	private CommonDao dao ;
	
	public Integer saveTradeRecord(TradeRecord tradeRecord) throws Exception{
		int userId = dao.saveEntity(tradeRecord);
		return tradeRecord.getId();
	}
	
	public Integer saveParty(Party item) throws Exception{
		int userId = dao.saveEntity(item);
		return item.getId();
	}
	
	public Integer savePartyLots(PartyLots item) throws Exception{
		int userId = dao.saveEntity(item);
		return item.getId();
	}
	
	public void addActivity(int party_id, String activity, long time) {
		try{
			UserActivity ua = new UserActivity(party_id,activity,time);
			dao.saveEntity(ua);
		}catch(Exception ex){
			
		}
	}
	
	public Party loadUserByName(String pName) throws Exception{
		return  dao.loadUserByName(pName);
	}
	
	public Integer saveLot(Lot item) throws Exception{
		int lotId = dao.saveEntity(item);
		return item.getId();
	}
	
	public Integer saveLotData(LotRate item) throws Exception{
		//dao.deleteLotData(item.getLot_id());
		int lotId = dao.saveEntity(item);
		return item.getId();
	}
	public boolean deleteLotDataByList(int listId){
		return dao.deleteLotDataByList(listId);
	}
	
	public Integer saveBid(Bid item) throws Exception{
		int lotId = dao.saveEntity(item);
		return item.getId();
	}
	
	public Boolean saveMultipleBids(List<Bid> items) throws Exception{
		int lotId = dao.saveMultipleEntity(items);
		return true;
	}

	public Integer saveTender(Tender item) throws Exception{
		int lotId = dao.saveEntity(item);
		return item.getId();
	}
	
	public boolean updateTender(Tender item) throws Exception{
		 boolean result = dao.updateEntity(item);
		return result;
	}
	
	public Integer saveFinancials(Financials item) throws Exception{
		int lotId = dao.saveEntity(item);
		return item.getId();
	}
	
	public boolean updateParty(Party item) throws Exception{
		 boolean result = dao.updateEntity(item);
		return result;
	}
	
	public int checkPartyExists(Party party){
		try{
			return dao.checkPartyExist(party);
		}catch(Exception ex){
			return -1;
		}
	}
	
	public int checkPartyExists_name(Party party){
		try{
			return dao.checkPartyExist_name(party);
		}catch(Exception ex){
			return -1;
		}
	}
	
	public List<Party> getAllParty(){
		try{
			return dao.getAllParty();
		}catch(Exception ex){
			return null;
		}
	}
	 
	public int saveLotList(LotList lotList){
		try{
			return dao.saveLotList(lotList);
		}catch(Exception ex){
			return 0;
		}
	}
	
	public int checkLotExists(Lot lot){
		try{
			return dao.checkLotExist(lot);
		}catch(Exception ex){
			return -1;
		}
	}
	
	public Party validateUser(Login login) {
		try{
			return dao.validateUser(login);
		}catch(Exception ex){
			return null;
		}
	}
	
	public int checkTenderExist(Tender party){
		try{
			return dao.checkTenderExist(party);
		}catch(Exception ex){
			return -1;
		}
	}
	
	public int getBidCount(Tender tender,Party party) throws Exception {
		try{
			return dao.getBidCount(tender,party);
		}catch(Exception ex){
			return -1;
		}
	} 
	
	public HashMap<Integer,Integer> getPartyBidCount(int tender_id) throws Exception{
		try{
			List<BidCount> bidCounts = dao.getPartyBidCount(tender_id);
			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			for (BidCount b: bidCounts){
				map.put(b.getParty_id(), b.getBids());
			}
			return map;
		}catch(Exception ex){
			return null;
		}
	}
	
	public List<ReportBean> getReport(String type,Integer[] party_ids,Integer[] tender_ids,int stateNo){
		try {
			if ("PARTY".equals(type)){
				return dao.getReportByParty(stateNo, party_ids,tender_ids);	
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<ReportBean>();
	}
	
	public List<ReportBean> getReport(String type,String[] lot_ids, int stateNo){
		return getReport(type, lot_ids, stateNo,null);
	}
	
	public List<ReportBean> getReport(String type,String[] lot_ids, int stateNo, Integer yearCount){
		try {
			if ("PARTY".equals(type)){
				return dao.getReportByParty(stateNo, null,null);	
			}else{
					/*String str_ids="";
					for (String id: ids){
						str_ids=str_ids+"'"+id+"',";
					}
					str_ids.substring(0,str_ids.length()-2);*/
				if (yearCount!=null){
					return dao.getReportByLot(lot_ids,stateNo,yearCount);
				}else{
					return dao.getReportByLot(lot_ids,stateNo);
				}
						
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<ReportBean>();
	}
	
	public void processAllotment(List<ResultBid> list){
		ResultBid bid=null ;
		
		if (list.size()>1){
			int selected_idx=-1;
			double minAmount=0;
			for (ResultBid result : list){
				double p_amount=0;
				if (map_partyAmount.containsKey(result.getParty_id())){
					p_amount= map_partyAmount.get(result.getParty_id());
				}

				if ( p_amount<minAmount || minAmount==0){
					minAmount=p_amount;
					bid=result;
				}else{
					continue;
				}
			}
		}else{
			bid=list.get(0);
		}
		
		double party_amt = map_financials.get(bid.getParty_id());
		Bid b = new Bid();
		b.setId(bid.getId());
		b.setIs_predicted(true);
		dao.updateBidStatus(b);
		//System.out.println("LOT Allotted to  "+ cur_lot + " party:  " + bid.getParty_id() + " Rank : " + bid.getRank());
		map_financials.put(bid.getParty_id(),party_amt-bid.getAmount());

		//keep the total amount alloted to party..
		if (map_partyAmount.containsKey(bid.getParty_id())){
			map_partyAmount.put(bid.getParty_id(),map_partyAmount.get(bid.getParty_id())+bid.getAmount());	
		}else{
			map_partyAmount.put(bid.getParty_id(),bid.getAmount());
		}
		
		lots_alloted+=bid.getLot_id()+",";
	}
	
	HashMap<Integer, Double> map_financials = new HashMap<Integer,Double>();
	HashMap<Integer, Double> map_partyAmount = new HashMap<Integer,Double>();
	String lots_alloted=",";
	
	
	
	
	public boolean resultProcessByTender(Tender tender,double minRate, String orderBy, boolean resetCancel,double capingRate) throws Exception {
		lots_alloted=",";
		try{
			dao.updateBidRankByTender(tender,resetCancel);
			List<Financials> list_financials =  dao.getPartyFinancialByTender(tender);
			
			for (Financials fin : list_financials){
				map_financials.put(fin.getParty_id(), fin.getPurchase_capacity()) ;
			}
			//List<ResultBid> list_bid= dao.getBidByTender_test(tender);
			//following code is working. commenting to check for rate and priority order..
			//List<ResultBid> list_bid= dao.getBidByTender(tender,false);
			List<ResultBid> list_bid= null;
			if (orderBy!=null && orderBy.equals("priority")){
				 list_bid= dao.getBidByTenderOrderRankPriority(tender, false, minRate,capingRate);
			}else{
				list_bid= dao.getBidByTenderOrderRankRate(tender,false,minRate);
			}
			
			int cur_rank=0;
			int cur_lot=-1;
	
			List<ResultBid> tmp_list= new ArrayList<ResultBid>();
			for (ResultBid bid : list_bid){
				
				if (lots_alloted.contains(","+bid.getLot_id()+",")){
					System.out.println("Lot is already Alotted :" + bid.getLot_id() + " Rank : " + bid.getRank());
					continue;
				}
				
				if (cur_lot!=bid.getLot_id().intValue()){
					
					//process the previous lot and save it...
					if (tmp_list.size()>0){
						processAllotment(tmp_list);
						tmp_list=new ArrayList<ResultBid>();
					}
					
					cur_lot=bid.getLot_id();
					System.out.println("New Lot Id:"+ cur_lot  + " Rank : " + bid.getRank());
					
					
				}				
				
				/*if (cur_lot_alloted){
					//skip these parties as this lot is already allote to party..
					System.out.println("Skippping Lot "+ cur_lot + " party:  " + bid.getParty_id() + " Rank : " + bid.getRank());
					continue;
				}*/
				
				if (map_financials.containsKey(bid.getParty_id())){
					double party_amt = map_financials.get(bid.getParty_id());
					if (party_amt>bid.getAmount()){
						// tender should be alotted to this party
						tmp_list.add(bid);
					}else{
						Bid b = new Bid();
						b.setId(bid.getId());
						dao.updateBidInSufficient(b);
						System.out.println("Insufficient Amount "+ cur_lot + " party:  " + bid.getParty_id() +  " Rank : " + bid.getRank());
						continue;
					}
				}
			}
		}catch(Exception ex){
			return false;
		}
		return true;
	}
	public void updateResult(Integer[] bid_ids, int year,int state){
		try {
			 dao.updateTenderResult(bid_ids, year ,state);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<ResultBid> getTederBids(int tender_id){
		Tender tender = new Tender();
		tender.setId(tender_id);
		List<ResultBid> list_bid=null;
		try {
			 list_bid= dao.getBidByTender(tender);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list_bid;
	}
	public List<Map<String,Object>> getTederYearBids(int year,int state){
		
		try {
			 return dao.getBidByTenderYear(year, state);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Map<String,Object>> getLotRatesYearWise(int state){
		
		try {
			 return dao.getLotRatesYearWise(state);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public boolean resultProcessByTender_updated(Tender tender) throws Exception {
		lots_alloted=",";
		try{
			dao.updateBidRankByTender(tender,true);
			List<Financials> list_financials =  dao.getPartyFinancialByTender(tender);
			
			for (Financials fin : list_financials){
				map_financials.put(fin.getParty_id(), fin.getPurchase_capacity()) ;
			}
			List<ResultBid> list_bid= dao.getBidByTender_test(tender);
			int cur_rank=0;
			int cur_lot=-1;
	
			List<ResultBid> tmp_list= new ArrayList<ResultBid>();
			for (ResultBid bid : list_bid){
				
				if (lots_alloted.contains(","+bid.getLot_id()+",")){
					System.out.println("Lot is already Alotted :" + bid.getLot_id() + " Rank : " + bid.getRank());
					continue;
				}
				
				/*if (cur_lot!=bid.getLot_id().intValue()){
					
					//process the previous lot and save it...
					if (tmp_list.size()>0){
						processAllotment(tmp_list);
						tmp_list=new ArrayList<ResultBid>();
					}
					
					cur_lot=bid.getLot_id();
					System.out.println("New Lot Id:"+ cur_lot  + " Rank : " + bid.getRank());
					
					
				}*/				
				
				/*if (cur_lot_alloted){
					//skip these parties as this lot is already allote to party..
					System.out.println("Skippping Lot "+ cur_lot + " party:  " + bid.getParty_id() + " Rank : " + bid.getRank());
					continue;
				}*/
				
				if (map_financials.containsKey(bid.getParty_id())){
					double party_amt = map_financials.get(bid.getParty_id());
					if (party_amt>bid.getAmount()){
						// tender should be alotted to this party
						
						tmp_list.add(bid);
						processAllotment(tmp_list);
						tmp_list=new ArrayList<ResultBid>(); 
					}else{
						Bid b = new Bid();
						b.setId(bid.getId());
						dao.updateBidInSufficient(b);
						System.out.println("Insufficient Amount "+ cur_lot + " party:  " + bid.getParty_id() +  " Rank : " + bid.getRank());
						//continue;
					}
				}
			}
		}catch(Exception ex){
			return false;
		}
		return true;
	}
	
	//this code is to allot tender by utilizing maxium capacity...
	public boolean resultProcessByTender_new(Tender tender) throws Exception {
		lots_alloted=",";
		try{
			dao.updateBidRankByTender(tender,true);
			List<Financials> list_financials =  dao.getPartyFinancialByTender(tender);
			
			for (Financials fin : list_financials){
				map_financials.put(fin.getParty_id(), fin.getPurchase_capacity()) ;
			}
			List<ResultBid> list_bid= dao.getBidByTenderByParty(tender);
			int cur_rank=-1;
			int cur_lot=-1;
			int cur_party=-1;
			
			List<ResultBid> tmp_list= new ArrayList<ResultBid>();
			ArrayList<Double> party_bids= new ArrayList<Double>();
			for (ResultBid bid : list_bid){
				
				if (lots_alloted.contains(","+bid.getLot_id()+",")){
					System.out.println("Lot is already Alotted :" + bid.getLot_id() + " Rank : " + bid.getRank());
					continue;
				}
				
				if (cur_party!=bid.getParty_id().intValue() || cur_rank!=bid.getRank().intValue()){
					
					//check if list size is greater than 0 then process the bids..
					if (tmp_list.size()>0){
						System.out.println("************************processing Party... " + cur_party + " -" + cur_rank + party_bids.size() );	
						if (party_bids.size()>20){
							System.out.println(12);
						}
						ArrayList<Double> party_alotment = Combination.sum_up(party_bids, map_financials.get(cur_party));
						System.out.println("allotments: " + party_alotment);
						for (ResultBid tmp_bid:tmp_list ){
							int idx = party_alotment.indexOf(tmp_bid.getAmount());
							if (idx>-1){
								double party_amt = map_financials.get(tmp_bid.getParty_id());
								Bid b = new Bid();
								b.setId(tmp_bid.getId());
								b.setIs_predicted(true);
								dao.updateBidStatus(b);
								//System.out.println("LOT Allotted to  "+ cur_lot + " party:  " + bid.getParty_id() + " Rank : " + bid.getRank());
								map_financials.put(tmp_bid.getParty_id(),party_amt-tmp_bid.getAmount());

								//keep the total amount alloted to party..
								if (map_partyAmount.containsKey(tmp_bid.getParty_id())){
									map_partyAmount.put(tmp_bid.getParty_id(),map_partyAmount.get(tmp_bid.getParty_id())+tmp_bid.getAmount());	
								}else{
									map_partyAmount.put(tmp_bid.getParty_id(),tmp_bid.getAmount());
								}
								
								lots_alloted+=tmp_bid.getLot_id()+",";
								System.out.println("lot allotted...... " + cur_party +" - " + tmp_bid.getLot_id() + tmp_bid.getAmount()  );
							}else{
								Bid b = new Bid();
								b.setId(tmp_bid.getId());
								dao.updateBidInSufficient(b);
								System.out.println("lot insufficient...... " + cur_party +" - " + tmp_bid.getLot_id() + tmp_bid.getAmount()  );
							}
						}
						tmp_list=new ArrayList<ResultBid>();
						party_bids= new ArrayList<Double>();
					}
					System.out.println("##############################Starting new Party... " + bid.getParty_id() + "-"+bid.getParty_name()+ "-" +  bid.getLot_id() +" - "+ bid.getAmount() );
					cur_party= bid.getParty_id();
					tmp_list.add(bid);
					party_bids.add(bid.getAmount());
					cur_rank = bid.getRank();
				}else{
					System.out.print("adding lot: " + bid.getParty_id() + "-" +  bid.getLot_id() +" - " );
					System.out.printf("%.2f\n",bid.getAmount());
					tmp_list.add(bid);
					party_bids.add(bid.getAmount()); 
				}
			}
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$Completed....$$$$$$$$$$$$$$$$$$$$$");
		}catch(Exception ex){
			System.out.println(ex);
			return false;
		}
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$Completed....$$$$$$$$$$$$$$$$$$$$$");
		return true;
		
	}
	
	public List<ReportBean> getTenderResult(int tenderId) throws Exception {
		try{
			return dao.getTenderResult(tenderId);
		}catch(Exception ex){
			return new ArrayList<ReportBean>();
		}
	}
	
	
	
	public boolean deleteBidByTender(Tender tender){
		try{
			return dao.deleteBidByTender(tender);
		}catch(Exception ex){
			return false;
		}
	}
	
	public List<ResultBid> getLotDetailsTenderWise(int lotId, int tenderId){
		
		try {
			return dao.getLotDetailsTenderWise(lotId, tenderId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList();
	}
	
	public List<LotData> getLotData(int lotId){
		
		try {
			return dao.getLotData(lotId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList();
	}
	
	
	public List<Tender> getAllTender(){
		try {
			return dao.getAllTender();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList();
	}
	
	public List<Lot> getAllLot(int state){
		try {
			return dao.getAllLot(state);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList();
	}
	
	public List<LotList> getLotList(){
		try {
			return dao.getLotList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList();
	}
	
	public List<Division> getAllDivision(){
		try {
			return dao.getAllDivision();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList();
	}
	
	public Party getParty(int id){
		try {
			return dao.getParty(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Tender getTender(int id){
		try {
			return dao.getTender(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<LotRate> getLotRate(int lotId){
		try {
			return dao.getLotRate(lotId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public Map<String,Integer> getMinMaxAvgRate(int tender_id){
		try {
			return dao.getMinMaxAvgRate(tender_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public Map<String,Integer> getLotMinMaxAvgRate(int lot_id){
		try {
			return dao.getLotMinMaxAvgRate(lot_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Map<String,Integer>> getYearWiseMinMaxAvgRate(int state_id){
		try {
			return dao.getYearWiseMinMaxAvgRate(state_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Map<String,Integer>> getLotYearWiseCount(int lot_id){
		try {
			return dao.getLotYearWiseCount(lot_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<Map<String,Integer>> getLotYearWiseMinMaxAvgRate(int lot_id){
		try {
			return dao.getLotYearWiseMinMaxAvgRate(lot_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Map<String,Double>> getYearWiseAmount(int state_id){
		try {
			return dao.getYearWiseAmount(state_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Map<String,Double>> getTop5Bidder(int tender_id){
		try {
			return dao.getTop5Bidder(tender_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Map<String,Double>> getTop5Lot(int tender_id){
		try {
			return dao.getTop5Lot(tender_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Map<String,Object>> getPartyBids(int tender_id,int party_id){
		try {
			return dao.getPartyBids(tender_id, party_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Map<String,Object>> getTenderResultDisplay(int tender_id){
		try {
			return dao.getTenderResultDisplay(tender_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Map<String,Double>> getDivisionWiseRates(int tender_id){
		try {
			return dao.getDivisionWiseRates(tender_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Map<String,String>> getPartyByTender(int tender_id){
		try {
			return dao.getPartyByTender(tender_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Map<String,String>> getTenderList(int state){
		try {
			return dao.getTenderList(state);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Map<String,Integer>> getLotCountRangeWise(int tender_id){
		try {
			return dao.getLotCountRangeWise(tender_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Map<String,Object>> getYearList(){
		try {
			return dao.getYearList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void updateResultByYearRate(int year,String lot_name,int rate,int state,String party_name){
		try {
			 dao.updateResultByYearRate(year, lot_name, rate, state,party_name);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void updateLotDetails( String lot_name, String lot_given_name,String division, int state ){
		try {
			 dao.updateLotDetails(lot_name, lot_given_name, division, state);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<PartyLots> getPartyLots(int party_id) throws Exception{
		return dao.getPartyLots(party_id);
	}

	public boolean checkPartyLotExists(int party_id, int lot_id) throws Exception{
		return dao.checkPartyLotExists(party_id, lot_id);
	}
	
}

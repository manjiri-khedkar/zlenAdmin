package mp.procurement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import mp.procurement.model.Bid;
import mp.procurement.model.Financials;
import mp.procurement.model.Lot;
import mp.procurement.model.Party;

public class ReadResult {

	
	static CommonService common;

	private void readResult() {
		// TODO Auto-generated method stub
		try {
			File f = new File("g:\\cg_result.txt");

			BufferedReader br = new BufferedReader(new FileReader(f));
			String cur_line = null;
			String companyName="";
			Double emd = 0.0, purCapacity = 0.0, bidCapacity = 0.0;
			List<Bid> list_bids = new ArrayList<Bid>();
			Financials financials = new Financials();
			int party_id = -1;
			int tender_id = 22;
			while ((cur_line = br.readLine()) != null) {

				if (cur_line.contains("Company Name : ")) {
					companyName = cur_line.replace("Company Name : ", "");
					Party party = new Party();
					party.setParty_name(companyName);
					party.setStatus(true);

					party_id = common.checkPartyExists_name(party);
				} else if (cur_line.contains("EMD : ")) {
					emd = Double.parseDouble(cur_line.replace("EMD : ", ""));
				} else if (cur_line.contains("Purchase Capacity : ")) {
					purCapacity = Double.parseDouble(cur_line.replace("Purchase Capacity : ", ""));
				} else if (cur_line.contains("Bidding Capacity : ")) {
					bidCapacity = Double.parseDouble(cur_line.replace("Bidding Capacity : ", ""));
					financials.setParty_id(party_id);
					financials.setTender_id(tender_id);
					financials.setEmd(emd);
					financials.setPurchase_capacity(purCapacity);
					financials.setBid_capacity(bidCapacity);
					// header=false;
				}
				if (cur_line.contains("Lot No.")) {
					cur_line = cur_line.replace("Lot. No.", " ");
					cur_line = cur_line.replace("Lot No.", " ");
					cur_line = cur_line.replace("S.B.", " ");
					cur_line = cur_line.replace("S.B", " ");
					cur_line = cur_line.replace("Quantity", " ");
					cur_line = cur_line.replace("-", " ");
					cur_line = cur_line.replace("    ", " ");
					cur_line = cur_line.replace("   ", " ");
					cur_line = cur_line.replace("  ", " ");
					String[] arr_col = cur_line.split(" ");
					int priority = Integer.parseInt(arr_col[0]);
					String lotName = arr_col[1];
					int qty = (int) Double.parseDouble(arr_col[2]);
					double rate = Double.parseDouble(arr_col[3]);
					double amount = Double.parseDouble(arr_col[4]);

					Lot lot = new Lot();
					lot.setLot_name(lotName);
					lot.setLot_given_name(lotName);
					lot.setQty(qty);
					lot.setSerial_number(0);
					lot.setState(2);
					int lot_id = common.checkLotExists(lot);

					Bid bid = new Bid();
					bid.setTender_id(tender_id);
					bid.setLot_id(lot_id);
					bid.setParty_id(party_id);
					bid.setRound_number(1);
					bid.setPriority(priority);
					bid.setQty(qty);
					bid.setRate(rate);
					bid.setAmount(amount);
					list_bids.add(bid);
				}
				if (cur_line.contains("Total")) {
					
					System.out.println("Party: "+ companyName +" : bid count:" + list_bids.size() );
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
}

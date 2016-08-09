package ucsf.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import ucsf.models.AccessionNumberBlock;
import ucsf.models.Profile;
import ucsf.models.Trial;

@Service
public class GeneralService {
	
	public Profile parseProfile(long id, JSONObject obj){
		Profile p = new Profile();
		p.setAnbId(id);
		p.setStatus("todo");
		try{
			p.setAge((String) obj.get("age"));
		}
		catch (Exception e){
			
		}
		try{
			p.setGender((String) obj.get("sex"));
		}
		catch (Exception e){
			
		}	
		try{
			p.setZipCode((String) obj.get("Zip Code"));
		}
		catch (Exception e){
			
		}	
		try{
			p.setCancerType((String) obj.get("cancertype"));
		}
		catch (Exception e){
			
		}	
		try{
			p.setCancerSubType((String) obj.get("cancersubtype"));
		}
		catch (Exception e){
			
		}	
		try{
			p.setClinicalStage((String) obj.get("cancerstage"));
		}
		catch (Exception e){
			
		}	
		try{
			p.setALK((String) obj.get("ALK"));
		}
		catch (Exception e){
			
		}	
		return p;
	}

	public List<Trial> parsetrials(long id, JSONObject trialsJson) {
		List<Trial> trials = new ArrayList<Trial>();
		Iterator<?> keys = trialsJson.keys();

		while( keys.hasNext() ) {
		    String key = (String)keys.next();
		    JSONObject value = new JSONObject();
		    if ( trialsJson.get(key) instanceof JSONObject ) {
		    	value = (JSONObject) trialsJson.get(key);
		    }
		    else
		    	continue;
		    Trial t = new Trial();
		    t.setProfileId(id);
		    t.settrialId(key);
		    try{
		    	t.setDist2closestsite(getJsonString(value, "dist2closestsite"));
			}
			catch (Exception e){
			}	
		    try{
		    	t.setFavourite(getJsonString(value, "favorite"));
			}
			catch (Exception e){
			}
		    try{
		    	t.setSitename(getJsonString(value, "sitename"));
			}
			catch (Exception e){
			}
		    try{
		    	t.setVA(getJsonString(value, "VA"));
			}
			catch (Exception e){
			}
		    try{
		    	t.setZipCode(getJsonString(value, "Zip Code"));
			}
			catch (Exception e){
			}
			trials.add(t);
		}
		
		return trials;
	}
	
	private String getJsonString(JSONObject json, String key){
		String s = new String();
		try{
			s = (String) json.getString(key);
		}
		catch(Exception e){
			
		}
		return s;
	}
}

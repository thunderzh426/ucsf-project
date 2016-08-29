package ucsf.services;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ucsf.dao.ProfileDao;
import ucsf.dao.TrialDao;
import ucsf.models.AccessionNumberBlock;
import ucsf.models.Profile;
import ucsf.models.Trial;

@Service
public class GeneralService {
	
	public Object processTodoFiles() {
		Map<String, Object> result = new HashMap<String, Object>();
	    try {
	    	File folder = new File(env.getProperty("ucsf.todo.file.path"));
	    	File[] listOfFiles = folder.listFiles();
	    	if (listOfFiles != null){
	    		for (File f: listOfFiles){
					ByteArrayInputStream bytestream = new ByteArrayInputStream(Files.readAllBytes(Paths.get(f.getAbsolutePath())));
					String str = IOUtils.toString(bytestream, StandardCharsets.UTF_8);
					JSONObject jsonObj = new JSONObject(str);
					JSONObject profileJson = jsonObj.getJSONObject("profile");
					JSONObject trialsJson = jsonObj.getJSONObject("trials");
					long id = Long.parseLong(FilenameUtils.getBaseName(f.getName()));
					Profile profile = parseProfile(id,profileJson);
					profileDao.create(profile);
					List<Trial> trials = parsetrials(profile.getId(), trialsJson);
					for(Trial t: trials){
					  trialDao.create(t);
					}
					Files.move(Paths.get(f.getAbsolutePath()), Paths.get(env.getProperty("ucsf.done.file.path"), f.getName()));
	    		}
	    	}
	    	result.put("success", true);
	    	return result;
	    }
	    catch (Exception ex) {
	      result.put("success", false);
	      result.put("message", ex.getMessage());
	      return result;
	    }
	  }
	
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
	
	@Autowired
	private Environment env;
	
	@Autowired
	private ProfileDao profileDao;
	
	@Autowired
	private TrialDao trialDao;
}

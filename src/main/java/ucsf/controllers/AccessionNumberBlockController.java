package ucsf.controllers;

import ucsf.dao.AccessionNumberBlockDao;
import ucsf.dao.ProfileDao;
import ucsf.dao.TrialDao;
import ucsf.models.AccessionNumberBlock;
import ucsf.models.Profile;
import ucsf.models.Trial;
import ucsf.services.GeneralService;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Class AccessionNumberBlockController
 */
@RestController
public class AccessionNumberBlockController {

  // ------------------------
  // PUBLIC METHODS
  // ------------------------
	
  @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> uploadFile(@RequestParam("uploadfile") MultipartFile uploadfile) {

    try {
      // Get the filename and build the local file path
      String filename = uploadfile.getOriginalFilename();
      //String basename = FilenameUtils.getBaseName(filename);
      String directory = env.getProperty("ucsf.todo.file.path");
      String filepath = Paths.get(directory, filename).toString();
      /*
      ByteArrayInputStream bytestream = new ByteArrayInputStream(uploadfile.getBytes());
      String str = IOUtils.toString(bytestream, StandardCharsets.UTF_8);
      JSONObject jsonObj = new JSONObject(str);
      JSONObject profileJson = jsonObj.getJSONObject("profile");
      JSONObject trialsJson = jsonObj.getJSONObject("trials");
      long id = Long.parseLong(basename);
      Profile profile = generalService.parseProfile(id,profileJson);
      profileDao.create(profile);
      List<Trial> trials = generalService.parsetrials(profile.getId(), trialsJson);
      for(Trial t: trials){
    	  trialDao.create(t);
      }*/
      // Save the file locally
      BufferedOutputStream stream =
          new BufferedOutputStream(new FileOutputStream(new File(filepath)));
      stream.write(uploadfile.getBytes());
      stream.close();
      return new ResponseEntity<>(HttpStatus.OK);
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    
  } // method uploadFile
  
  @RequestMapping(value="/todolist", method = RequestMethod.GET)
  @ResponseBody
  public Object getTodoList() {
    try {
    	File folder = new File(env.getProperty("ucsf.todo.file.path"));
    	File[] listOfFiles = folder.listFiles();
    	List<String> filenames = new ArrayList<String>();
    	if (listOfFiles != null){
    		for (File f: listOfFiles){
    			filenames.add(f.getName());
    		}
    	}
    	return filenames;
    }
    catch (Exception ex) {
      return "Error getting todo list" + ex.toString();
    }
  }
  
  @RequestMapping(value="/donelist", method = RequestMethod.GET)
  @ResponseBody
  public Object getDoneList() {
    try {
    	File folder = new File(env.getProperty("ucsf.done.file.path"));
    	File[] listOfFiles = folder.listFiles();
    	List<String> filenames = new ArrayList<String>();
    	if (listOfFiles != null){
    		for (File f: listOfFiles){
    			filenames.add(f.getName());
    		}
    	}
    	return filenames;
    }
    catch (Exception ex) {
      return "Error getting donelist. " + ex.toString();
    }
  }
  
  @RequestMapping(value="/donelist", method = RequestMethod.DELETE)
  @ResponseBody
  public Object deleteDoneList() {
    try {
    	File folder = new File(env.getProperty("ucsf.done.file.path"));
    	File[] listOfFiles = folder.listFiles();
    	if (listOfFiles != null){
    		for (File f: listOfFiles){
    			f.delete();
    		}
    	}
    	return new ResponseEntity<>(HttpStatus.OK);
    }
    catch (Exception ex) {
      return "Error deleting done list. " + ex.toString();
    }
  }
  
  
  @RequestMapping(value="/todolist/process", method = RequestMethod.POST)
  @ResponseBody
  public Object processTodoFiles() {
	  return generalService.processTodoFiles();
  }
  
  /**
   * Create a new user with an auto-generated id and email and name as passed 
   * values.
   */
  @RequestMapping(value="/create", method = RequestMethod.POST)
  @ResponseBody
  public Object create(@RequestBody AccessionNumberBlock anb) {
    try {
      anbDao.create(anb);
    }
    catch (Exception ex) {
      return "Error creating the AccessionNumberBlock: " + ex.toString();
    }
    return anb;
  }
  
  /**
   * Delete the user with the passed id.
   */
  @RequestMapping(value="/delete")
  @ResponseBody
  public String delete(long id) {
    try {
      AccessionNumberBlock anb = new AccessionNumberBlock(id);
      anbDao.delete(anb);
    }
    catch (Exception ex) {
      return "Error deleting the AccessionNumberBlock: " + ex.toString();
    }
    return "AccessionNumberBlock succesfully deleted!";
  }
  
  @RequestMapping(value="/profile/{id}/trials")
  @ResponseBody
  public Object getProfileById(@PathVariable long id) {
    try {
      List<Trial> trials = trialDao.getByProfileId(id);
      Profile profile = profileDao.getProfileById(id);
      Map<String, Object> result = new HashMap<String, Object>();
      result.put("profile", profile);
      result.put("trials", trials);
      return result;
    }
    catch (Exception ex) {
      return "Error deleting the AccessionNumberBlock: " + ex.toString();
    }
  }
  
  @RequestMapping(value="/trial/{id}")
  @ResponseBody
  public Object getTrialById(@PathVariable long id) {
    try {
      Trial trial = trialDao.getBytrialId(id);
      return trial;
    }
    catch (Exception ex) {
      return "Error getting trial: " + ex.toString();
    }
  }
  
  @RequestMapping(value="/trials")
  @ResponseBody
  public Object getTrialById() {
    try {
      return trialDao.getAll();
    }
    catch (Exception ex) {
      return "Error getting trial: " + ex.toString();
    }
  }
  
  @RequestMapping(value="/profiles")
  @ResponseBody
  public Object getProfiles() {
    try {
      return profileDao.getAll();
    }
    catch (Exception ex) {
      return "Error getting profiles: " + ex.toString();
    }
  }
  
  @RequestMapping(value="/anbs")
  @ResponseBody
  public Object getAnbs() {
    try {
      return anbDao.getAll();
    }
    catch (Exception ex) {
      return "Error getting anb: " + ex.toString();
    }
  }
  
  @RequestMapping(value="/anb/{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public Object deleteAnbById(@PathVariable Long id) {
    try {
      anbDao.delete(new AccessionNumberBlock(id));
      List<Profile> profiles = profileDao.getByAccessionBlockId(id);
      for (Profile profile:profiles){
    	  trialDao.deleteByProfileId(profile.getId());
    	  profileDao.delete(profile);
      }
      return new ResponseEntity<>(HttpStatus.OK);
    }
    catch (Exception ex) {
      return "Error getting anb: " + ex.toString();
    }
  }
  
  @RequestMapping(value="/profile/{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public Object deleteProfileById(@PathVariable Long id) {
    try {
	  Profile profile = new Profile(id);
	  trialDao.deleteByProfileId(id);
	  profileDao.delete(profile);
      return "Deleted";
    }
    catch (Exception ex) {
      return "Error getting anb: " + ex.toString();
    }
  }
  
  @RequestMapping(value="/trial/{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public Object deleteTrialById(@PathVariable Long id) {
    try {
	  trialDao.delete(new Trial(id));
      return "Deleted";
    }
    catch (Exception ex) {
      return "Error getting anb: " + ex.toString();
    }
  }
  
  @RequestMapping(value="/trials", method = RequestMethod.DELETE)
  @ResponseBody
  public Object deleteAllTrials() {
    try {
	  trialDao.deleteAll();
      return "Deleted";
    }
    catch (Exception ex) {
      return "Error getting anb: " + ex.toString();
    }
  }
  
  @RequestMapping(value="/profiles", method = RequestMethod.DELETE)
  @ResponseBody
  public Object deleteAllProfiles() {
    try {
      List<Profile> profiles = profileDao.getAll();
      for (Profile profile: profiles){
    	  trialDao.deleteByProfileId(profile.getId());
      }
      profileDao.deleteAll();
      return "Deleted";
    }
    catch (Exception ex) {
      return "Error getting anb: " + ex.toString();
    }
  }
  
  @RequestMapping(value="/anbs", method = RequestMethod.DELETE)
  @ResponseBody
  public Object deleteAllAnbs() {
    try {
      List<AccessionNumberBlock> anbs = anbDao.getAll();
      for (AccessionNumberBlock anb: anbs){
    	  deleteAnbById(anb.getAccessionId());
      }
      return new ResponseEntity<>(HttpStatus.OK);
    }
    catch (Exception ex) {
      return "Error getting anb: " + ex.toString();
    }
  }
  

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  // Wire the Dao used inside this controller.
  @Autowired
  private Environment env;
  
  @Autowired
  private AccessionNumberBlockDao anbDao;
  
  @Autowired
  private ProfileDao profileDao;
  
  @Autowired
  private TrialDao trialDao;
  
  @Autowired
  private GeneralService generalService;
  
}

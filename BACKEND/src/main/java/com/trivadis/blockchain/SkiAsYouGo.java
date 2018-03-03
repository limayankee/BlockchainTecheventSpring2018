

package com.trivadis.blockchain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trivadis.blockchain.model.LiftEvent;
import com.trivadis.blockchain.model.UserLiftEvents;
import org.hyperledger.java.shim.ChaincodeBase;
import org.hyperledger.java.shim.ChaincodeStub;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * <h1>"Hello world" Chaincode</h1>
 *<b> Features:</b>
 *git
 *<li> - in addition to framework-propagated (put\del\query) functions 
 * also provides no-op function "hello" so it could be invoked without executing stub code  @see shim.ChaincodeStub
 * <li>- query will greet you regardless if argument found in the map, but if found it will be personal greeting
 * <li>- put and del work as you would expect for Map implementation
 * 
 * <h2>Meant as default java Chaincode implementation e.g. invoked by default and required no init\prep work to check finctionality</h2> 
 * steps to invoke chaincode functions in dev mode:
 * <li>1. run node in dev mode e.g.:
'export CORE_LOGGING_LEVEL=debug
./peer node start --peer-chaincodedev'
 *<li>2.  run chaincode e.g. via gradle:
	'gradle run'
 *<li>3. Now we can communicate to chaincode via peer:
 <br>
<code>
./peer chaincode deploy -n hello -c '{"Function":"init","Args":[]}'<br>
./peer chaincode query -n hello -c '{"Function":"put","Args":["Me"]}'<br>
- get you argument echo back if not found  in the map <br><br>

./peer chaincode invoke -n hello -c '{"Function":"hello","Args":[""]}'<br>
- no-op test. invoke chaincode, but  not ChaincodeStub @see shim.ChaincodeStub, Handler @see shim.Handler 
hence no channel call and only effect is line in stdout<br><br>

./peer chaincode invoke -n hello -c '{"Function":"put","Args":["hey","me"]}'<br>
- put your name on the map<br><br>

./peer chaincode query -n hello -c '{"Function":"put","Args":["hey"]}'<br>
- get you argument echo back if not found  in the map<br><br>

./peer chaincode query -n hello -c '{"Function":"put","Args":["hey"]}'<br>
- personal greeting for mapped name<br>
</code>
 * 
 * @author Sergey Pomytkin spomytkin@gmail.com
 *
 */
public class SkiAsYouGo extends ChaincodeBase {
	 private static Log log = LogFactory.getLog(SkiAsYouGo.class);

	ObjectMapper mapper = new ObjectMapper();

	@Override
	public String run(ChaincodeStub stub, String function, String[] args) {
		log.info("In run, function:"+function);
		switch (function) {
		case "put":
			String userId = args[0];
			String jsonEvent = args[1];
			UserLiftEvents liftEvents;

			log.debug("userId="+userId);
			log.debug("jsonEvent="+jsonEvent);

			try {
				LiftEvent event = mapper.readValue(jsonEvent, LiftEvent.class);
				if (stub.getState(userId)!=null&&!stub.getState(userId).isEmpty()){
					liftEvents =  mapper.readValue(stub.getState(userId), UserLiftEvents.class);
					liftEvents.addEvent(event);

				}
				else {
					liftEvents = new UserLiftEvents(userId);
					liftEvents.addEvent(event);
				}

				stub.putState(userId,mapper.writeValueAsString(liftEvents));
			}
			catch (Exception e) {
				log.error(e);
				return null;
			}

			break;
		case "del":
			for (String arg : args)
				stub.delState(arg);
			break;
		case "hello":
			System.out.println("hello invoked");
			log.info("hello invoked");
			break;
		}
		log.error("No matching case for function:"+function);
		return null;
	}

	@Override
	public String query(ChaincodeStub stub, String function, String[] args) {
		log.info("query");
		System.out.println("Hello world! function:"+function);
		log.debug("query:"+args[0]+"="+stub.getState(args[0]));
		if (stub.getState(args[0])!=null&&!stub.getState(args[0]).isEmpty()){
			log.trace("returning: Hello world! from "+ stub.getState(args[0]));
			return "Hello world! from "+ stub.getState(args[0]);
		}else{
			log.debug("No value found for key '"+args[0]+"'");
			return "No value found for key "+args[0]+"!";
		}
	}

	@Override
	public String getChaincodeID() {
		return "hello";
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Hello world! starting "+args);
		log.info("starting");
		new SkiAsYouGo().start(args);
	}


}

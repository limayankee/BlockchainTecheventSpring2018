package trivadis.blockchain;

import org.hyperledger.java.shim.ChaincodeBase;
import org.hyperledger.java.shim.ChaincodeStub;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class SkiAsYouGo extends ChaincodeBase {
    private static Log log = LogFactory.getLog(Example.class);

    @Override
    public String run(ChaincodeStub stub, String function, String[] args) {
        log.info("In run, function:"+function);
        switch (function) {
            case "put":
                for (int i = 0; i < args.length; i += 2)
                    stub.putState(args[i], args[i + 1]);
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
        new Example().start(args);
    }


}
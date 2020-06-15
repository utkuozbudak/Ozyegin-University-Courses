package nego2020.group9;

import geniusweb.actions.*;

import geniusweb.issuevalue.*;
import geniusweb.party.Capabilities;
import geniusweb.party.DefaultParty;
import geniusweb.party.inform.*;
import geniusweb.profileconnection.ProfileConnectionFactory;
import geniusweb.profileconnection.ProfileInterface;
import geniusweb.progress.Progress;
import org.apache.commons.math.stat.regression.OLSMultipleLinearRegression;
import tudelft.utilities.logging.Reporter;

import javax.swing.tree.ExpandVetoException;
import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Level;

public class MyAgent extends DefaultParty{

    private static final BigDecimal N09 = new BigDecimal("0.9");
    private Bid lastReceivedBid = null; // we ignore all others
    private PartyId me;
    private Random random;
    private OLSMultipleLinearRegression regression;
    protected ProfileInterface profileint;
    private SimpleLinearOrdering estimatedProfile = null;
    private List<List<String>> allPossibleBids;
    private List<Bid> sortedPredictedBids;
    private int turn_count =0;
    private List<Bid> opponets_bid;
    private List<Bid> my_bids;
    private final double constant_k_fromsurvey=0.10625;
    private int no_of_givenbids;
    private int current_bid;

    public MyAgent(){}
    public MyAgent(Reporter reporter) {super(reporter);}
    @Override
    public Capabilities getCapabilities() {
        return new Capabilities(new HashSet<>(Arrays.asList("SHAOP")));
    }


    @Override
    public String getDescription() {    return "DUO AGENT";    }

    @Override
    public void notifyChange(Inform info) {
        try {
            if (info instanceof Settings) {
                Settings settings = (Settings) info;
                this.profileint = ProfileConnectionFactory.create(settings.getProfile().getURI(), getReporter());
                this.me = settings.getID();
                Progress progress = settings.getProgress();
                this.opponets_bid =new ArrayList<>();
                this.sortedPredictedBids= new ArrayList<>();
                this.my_bids= new ArrayList<>();
                current_bid=0;
                estimatedProfile = new SimpleLinearOrdering(profileint.getProfile());
                elicitation();
            } else if (info instanceof ActionDone) {
                Action otheract = ((ActionDone) info).getAction();
                if (otheract instanceof Offer) {
                    lastReceivedBid = ((Offer) otheract).getBid();
                    opponets_bid.add(lastReceivedBid);
                } else if (otheract instanceof Comparison) {

                    estimatedProfile = estimatedProfile.with(
                            ((Comparison) otheract).getBid(),
                            ((Comparison) otheract).getWorse());
                    turn_count++;
                    myTurn();
                }
            } else if (info instanceof YourTurn) {
                turn_count++;
                myTurn();
            } else if (info instanceof Finished) {
                getReporter().log(Level.INFO, "Final outcome:" + info);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to handle info", e);
        }

    }

    public void myTurn() throws Exception{
        Action actiontotake;
        boolean chec=true;
        if(turn_count>5){
            double sum=0;
            for(int i=my_bids.size()-1;i>=0&&my_bids.size()>1;i--){
               double temp_p= (calculate_weight(my_bids.get(i))-calculate_weight(my_bids.get(i+1)))/calculate_weight(my_bids.get(i));
               sum+=temp_p;
            }
            sum/=(my_bids.size()-1);
            if(my_bids.size()>1&&calculate_weight(lastReceivedBid)<(1-(sum*constant_k_fromsurvey))*calculate_weight(my_bids.get(my_bids.size()-1))*calculate_weight(estimatedProfile.getReservationBid()))
            {
                actiontotake= new EndNegotiation(me);
                getConnection().send(actiontotake);
                chec=false;
            }
            else if(my_bids.size()>1&&calculate_weight(lastReceivedBid)>=calculate_weight(my_bids.get(my_bids.size()-1)));{
                actiontotake = new Accept(me,lastReceivedBid);
                getConnection().send(actiontotake);
                chec=false;
            }
        }
        if(chec){
            Bid bid = sortedPredictedBids.get(current_bid);
            actiontotake = new Offer(me,bid);
            current_bid++;
            getConnection().send(actiontotake);
        }
    }
    public double calculate_weight(Bid x){
        return 1.0-((double)sortedPredictedBids.indexOf(x)/(double)sortedPredictedBids.size());
    }
    public static List<List<String>> generateAllPossibleBids(List<List<String>> input, int i) {

        if (i == input.size()) {
            List<List<String>> result = new ArrayList<List<String>>();
            result.add(new ArrayList<String>());
            return result;
        }

        List<List<String>> result = new ArrayList<List<String>>();
        List<List<String>> recursive = generateAllPossibleBids(input, i + 1);

        for (int j = 0; j < input.get(i).size(); j++) {
            for (int k = 0; k < recursive.size(); k++) {
                List<String> newList = new ArrayList<String>(recursive.get(k));
                newList.add(input.get(i).get(j));
                result.add(newList);
            }
        }
        return result;
    }
    public void elicitation(){
        int howmanyvalue=0;
        try {

            List<List<String>> allValues = new ArrayList<>();
            List<List<Value>> allValuesAsValues = new ArrayList<>();
            List<String> types = new ArrayList<>();
            Set<String> issues = profileint.getProfile().getDomain().getIssues();

            for (String s: issues){
                ArrayList<String> temp= new ArrayList<>();
                ArrayList<Value> temp_v=new ArrayList<>();
                ValueSet vs= profileint.getProfile().getDomain().getValues(s);
                for(Value v: vs){
                    howmanyvalue++;
                    if(v instanceof DiscreteValue) {
                        temp.add(((DiscreteValue) v).getValue());
                        temp_v.add(v);
                        types.add("Discrete");
                    }
                    else if(v instanceof NumberValue) {
                        temp.add(((NumberValue) v).getValue().toString());
                        temp_v.add(v);
                        types.add("Number");
                    }
                }
                allValues.add(temp);
                allValuesAsValues.add(temp_v);
            }
            this.allPossibleBids = generateAllPossibleBids(allValues,0);
            double [][] encodedValues= oneHotEncoder(allPossibleBids,howmanyvalue,allValuesAsValues);

            List<List<String>> givenBids= new ArrayList<>();
            List<Bid> sb= estimatedProfile.getSortedBids((profileint.getProfile()));
            this.no_of_givenbids = sb.size();
            for(Bid b: sb){
                List<String> temp_ls=new ArrayList<>();
                for(String is: issues){
                    if(b.getValue(is) instanceof DiscreteValue) {
                        temp_ls.add(((DiscreteValue) b.getValue(is)).getValue());
                    }
                    else if(b.getValue(is) instanceof NumberValue) {
                        temp_ls.add(((NumberValue) b.getValue(is)).getValue().toString());
                    }
                }
                givenBids.add(temp_ls);
            }
            double[][] encodedTrainValues= oneHotEncoder(givenBids,howmanyvalue,allValuesAsValues);
            double[] indexes= new double[encodedTrainValues.length];
            for(int i=1;i<=indexes.length;i++) indexes[i-1]=i*2;
            regression=new OLSMultipleLinearRegression();
            double[] bid_prediction= new double[encodedValues.length];
            boolean axet = true;
            try {
                regression.newSampleData(indexes, encodedTrainValues);
            }
            catch (Exception e){
                for(int i=0 ;i<encodedValues.length; i++){
                    random= new Random();
                    bid_prediction[i] = random.nextDouble();
                    axet=false;
                }
            }

            try {
                for (int i = 0; i < encodedValues.length&&axet; i++) {
                    bid_prediction[i] = predict(regression, encodedValues[i]);
                }
            }
            catch (Exception e){
                for(int i=0 ;i<encodedValues.length; i++){
                    random= new Random();
                    bid_prediction[i] = random.nextDouble();
                }
            }
            List<Bid> lob = new ArrayList<>();
            for(int i=0;i<encodedValues.length;i++) {
                Map<String, Value> msv = new HashMap<>();
                for(String s: issues){
                    for(int j=0;j<allPossibleBids.get(i).size();j++) {
                        if (types.get(j).equals("Discrete")&&profileint.getProfile().getDomain().getValues(s).toString().contains(allPossibleBids.get(i).get(j)))
                            msv.put(s, new DiscreteValue(allPossibleBids.get(i).get(j)));
                        else if(types.get(j).equals("Number")&&profileint.getProfile().getDomain().getValues(s).toString().contains(allPossibleBids.get(i).get(j)))
                            msv.put(s, new NumberValue(allPossibleBids.get(i).get(j)));
                    }
                }
                sortedPredictedBids.add(new Bid(msv));
                lob.add(new Bid(msv));
            }

            sortedPredictedBids.sort(Comparator.comparing(s ->bid_prediction[lob.indexOf(s)]));


        }
        catch (Exception e){
                System.out.println("Couldn't elicitate");
                e.printStackTrace();
        }
    }
    public static double[][] oneHotEncoder(List<List<String>> bidOrder, int countAll, List<List<Value>> allIssues) {
        double[][] oneHotEncoded = new double[bidOrder.size()][countAll];
        int count = 0;
        for (int i = 0; i < oneHotEncoded.length; i++) {
            for (int j = 0; j < oneHotEncoded[0].length; j++) {
                for (int k = 0; k < bidOrder.get(i).size(); k++) {
                    for (int l = 0; l < allIssues.get(k).size(); l++) {
                        boolean cget=true;
                        if(bidOrder.get(i).get(k)==null) oneHotEncoded[i][count]=0.5;
                        else if(allIssues.get(k).get(l) instanceof DiscreteValue) {
                            if (bidOrder.get(i).get(k).equals(((DiscreteValue) allIssues.get(k).get(l)).getValue())) {
                                oneHotEncoded[i][count] = 1.0;
                                cget=false;
                            }
                        }
                        else if(allIssues.get(i).get(k) instanceof NumberValue){
                            if (bidOrder.get(i).get(k).equals(((NumberValue) allIssues.get(k).get(l)).getValue().toString())) {
                                oneHotEncoded[i][count] = 1.0;
                                cget=false;
                            }
                        }
                        if (cget){
                            oneHotEncoded[i][count] = 0.0;
                        }
                        count++;
                    }
                }
                count = 0;
            }
        }
        return oneHotEncoded;
    }

    public static double predict(OLSMultipleLinearRegression regression, double[] x) {
        if (regression == null) {
            throw new IllegalArgumentException("Null Object");
        }
        double[] params = regression.estimateRegressionParameters();
        double prediction = params[0];
        for (int i = 1; i < params.length; i++) {
            prediction += params[i] * x[i - 1];
        }
        return prediction;
    }

}

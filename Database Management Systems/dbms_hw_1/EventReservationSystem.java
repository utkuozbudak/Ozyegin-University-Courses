import javax.xml.transform.Result;
import java.sql.*;
import java.util.Scanner;

public class ​EventReservationSystem {

private static final String DB_URL = "jdbc:mysql://localhost:3306/CS202?useSSL=false";

private static final String USER = "root";
private static final String PASS = ***********;

private static Connection conn = null;
private static Statement stmt = null;
private static PreparedStatement pstmt = null;
private static PreparedStatement pstmt2 = null;


public void init() {

        try {
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.createStatement();
        System.out.println("Connection successful.");

        } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Problem!");

        }
        }

public boolean addOrganizer(String str){
        boolean success = false;
        try{
        pstmt = conn.prepareStatement("INSERT INTO organizer VALUES(?)");
        pstmt.setString(1,str);
        pstmt.executeUpdate();
        success = true;
        System.out.println("Addition is done.");
        }
        catch(Exception e){
        e.printStackTrace();
        }
        return success;
        }

public boolean removeOrganizer(String str){
        boolean success = false;
        try{
        pstmt = conn.prepareStatement("DELETE FROM organizer WHERE organizer_name = ?");
        pstmt.setString(1,str);
        pstmt.executeUpdate();
        System.out.println("Organizer removed successfully.");
        success = true;

        }
        catch(Exception e){

        System.out.println("Operation failed. There is no organizer with name " + str);

        }
        return success;
        }

public boolean addLocation(String name){
        boolean success = false;
        try{
        pstmt = conn.prepareStatement("INSERT INTO location VALUES(?)");
        pstmt.setString(1,name);
        pstmt.executeUpdate();
        System.out.println("Location addition successful.");
        success = true;



        }
        catch(Exception e){
        e.printStackTrace();
        }
        return success;
        }

public boolean removeLocation(String str){
        boolean success = false;
        try{
        pstmt2 = conn.prepareStatement("DELETE FROM EventType WHERE locName = ?");
        pstmt2.setString(1,str);
        pstmt2.executeUpdate();
        pstmt = conn.prepareStatement("DELETE FROM location WHERE location.location_name = ?");
        pstmt.setString(1,str);
        pstmt.executeUpdate();
        System.out.println("Location removed successfully.");
        success = true;

        }
        catch(Exception e){
        System.out.println("Operation failed. There is no location with name " + str);

        }
        return success;
        }

public boolean makeReservation(String id,String eventType, String oname, String lname, String day, int start, int end){
        boolean success= true;
        try{

        pstmt=conn.prepareStatement("INSERT INTO reserves VALUES(?,?,?,?,?,?,?)");
        pstmt.setString(1,id);
        pstmt.setString(2,oname);
        pstmt.setString(3,lname);
        pstmt.setString(4,day);
        pstmt.setInt(5,start);
        pstmt.setInt(6,end);
        pstmt.setString(7,eventType);
        pstmt2 = conn.prepareStatement("SELECT event_type FROM EventType WHERE locName = ? ");
        pstmt2.setString(1,lname);

        ResultSet rs = pstmt2.executeQuery();
        while(rs.next())
        {
        String temp= rs.getString(1);

        if (eventType.equals(temp)) {
        if(end>start){
        ResultSet rs2=stmt.executeQuery("SELECT day,start,end FROM reserves");
        boolean isOkey=true;
        while(rs2.next()){
        if(rs2.getString(1).equals(day)&&((rs2.getInt(2)<=end&&rs2.getInt(2)>=start)||(rs2.getInt(3)<=end&&rs2.getInt(3)>=start))){
        isOkey=false;
        }
        }
        if(isOkey) {
        pstmt.executeUpdate();
        System.out.println("Reservation successfull.");
        success = true;
        break;
        }
        else
        System.out.println("ERROR! Your reservation coincidence with another reservation. ");
        }
        else
        System.out.println("ERROR! Ending time must greater than starting time. ");
        }

        }


        }
        catch (Exception e){
        e.printStackTrace();
        }
        return success;
        }

public boolean cancelReservation(String str){
        boolean success = false;

        try{
        pstmt = conn.prepareStatement("DELETE FROM reserves WHERE res_id = ?");
        pstmt.setString(1,str);
        pstmt.executeUpdate();
        System.out.println("Reservation cancelled successfully.");
        success = true;


        }
        catch(Exception e){

        System.out.println("Operation failed. There is no reservation with ID " + str);

        }
        return success;

        }

public boolean listAllReservations(){
        boolean success = false;
        try{
        ResultSet rs = stmt.executeQuery("SELECT * FROM reserves");
        while(rs.next()){
        System.out.println("Reservation id: " + rs.getString(1));
        System.out.println("Organizer name: " + rs.getString(2));
        System.out.println("Location name: " + rs.getString(3));
        System.out.println("Date: " + rs.getString(4));
        System.out.println("Start time: " + rs.getInt(5) +" "+ "End time: " + rs.getInt(6));
        System.out.println("Event type: " + rs.getString(7));
        System.out.println("---------------------------------------------");

        }
        }
        catch(Exception e){
        e.printStackTrace();

        }
        return success;


        }

public boolean reservationsOfLocation(String str){
        boolean success = false;
        try{
        pstmt = conn.prepareStatement("SELECT res_id FROM reserves WHERE reserves.loc_name = ?");
        pstmt.setString(1,str);
        ResultSet rs = pstmt.executeQuery();
        System.out.println(str + "'s Reservations: ");
        while(rs.next()) {
        System.out.println(rs.getString(1));
        success = true;
        }



        }
        catch(Exception e){
        e.printStackTrace();
        }
        return success;
        }

public boolean reservationsOfOrganizer(String str){
        boolean success = false;
        try{
        pstmt = conn.prepareStatement("SELECT * FROM reserves WHERE org_name = ?");
        pstmt.setString(1,str);
        ResultSet rs = pstmt.executeQuery();
        System.out.println(str + "'s Reservations: ");
        while(rs.next()){
        System.out.println(rs.getString(1));
        success = true;

        }


        }
        catch(Exception e){
        System.out.println("Operation failed. There is no organizer with name: " + str);
        }
        return success;
        }

public boolean showOrganizerOfReservation(String str){
        boolean success = false;
        try{
        pstmt = conn.prepareStatement("SELECT org_name FROM reserves WHERE res_id = ?");
        pstmt.setString(1,str);
        ResultSet rs = pstmt.executeQuery();
        System.out.println("Organizer of the reservation id '" + str + "' is: ");
        while(rs.next()){
        System.out.println(rs.getString(1));
        }


        }
        catch(Exception e){
        System.out.println("Operation failed. There is no reservation id such as: " + str);

        }
        return success;
        }

public boolean reservationOfParticularEvent(String str){
        boolean success = false;
        try{
        pstmt = conn.prepareStatement("SELECT res_id FROM reserves WHERE reserves.type = ?");
        pstmt.setString(1,str);
        ResultSet rs = pstmt.executeQuery();
        System.out.println("List of reservations with " + str + " event type: ");
        while(rs.next()){
        System.out.println(rs.getString(1));
        success = true;
        }

        }
        catch(Exception e){
        System.out.println("Operation failed. There is no event type such as " + str);
        }
        return success;
        }


public static void main(String[] args) {
        EventReservationSystem ex = new EventReservationSystem();
        ex.init();


        Scanner scan = new Scanner(System.in);

        System.out.println("Room Reservation System created by Utku Ozbudak \n");

        while (true) {
        System.out.println("1. Add Organizer \n" +
        "2. Remove Organizer \n" +
        "3. Add Location \n" +
        "4. Remove Location \n" +
        "5. Make reservation \n" +
        "6. Cancel reservation \n" +
        "7. List all reservations \n" +
        "8. List all reservations of a Location \n" +
        "9. List all reservations of an Organizer \n" +
        "10.Show Organizer of a particular reservation \n" +
        "11.List all reservations of a Particular Event Type(Concert, Meeting or Sports Organization) \n");


        int next = scan.nextInt();

        switch (next) {
        case 1:
        System.out.println("Enter an organizer name: ");
        String organizerName = scan.next();
        ex.addOrganizer(organizerName);
        break;
        case 2:
        System.out.println("Enter the name of organizer to be romoved: ");
        String orgName = scan.next();
        ex.removeOrganizer(orgName);
        break;
        case 3:
        System.out.println("Enter the name of the location: ");
        String locName = scan.next();
        ex.addLocation(locName);
        String exit = "";
        while(true){
        System.out.println("Enter event type(s) for this location: (type 0 to finish)");
        exit = scan.next();
        if(exit.equals("0")) {
        break;
        }
        try{
        pstmt = conn.prepareStatement("INSERT INTO EventType VALUES(?,?)");
        pstmt.setString(1,locName);
        pstmt.setString(2,exit);
        pstmt.executeUpdate();
        }
        catch(Exception e){
        e.printStackTrace();
        }

        }
        break;
        case 4:
        System.out.println("Enter the name of the location to be removed: ");
        String lName = scan.next();
        ex.removeLocation(lName);
        break;
        case 5:
        System.out.println("Enter a reservation id with 5 character: ");
        String resID = scan.next();
        System.out.println("Enter event type: ");
        String eType = scan.next();
        System.out.println("Enter organizer's name: ");
        String oName = scan.next();
        System.out.println("Enter location's name: ");
        String locname = scan.next();
        System.out.println("Enter the day of event: ");
        String day = scan.next();
        System.out.println("Enter the starting time: ");
        int start = scan.nextInt();
        System.out.println("Enter the ending time: ");
        int end = scan.nextInt();

        ex.makeReservation(resID, eType, oName, locname, day, start, end);
        break;

        case 6:
        System.out.println("Enter reservation id to cancel reservation: ");
        String resid = scan.next();
        ex.cancelReservation(resid);
        break;

        case 7:
        ex.listAllReservations();
        break;

        case 8:
        System.out.println("Enter location's name: ");
        String loc = scan.next();
        ex.reservationsOfLocation(loc);
        break;

        case 9:
        System.out.println("Enter organizer's name: ");
        String org = scan.next();
        ex.reservationsOfOrganizer(org);
        break;

        case 10:
        System.out.println("Enter reservation id to see organizer's name: ");
        String id = scan.next();
        ex.showOrganizerOfReservation(id);
        break;

        case 11:
        System.out.println("Enter event type to see all kind of reservations: ");
        String etype = scan.next();
        ex.reservationOfParticularEvent(etype);
        break;
        }
        }
        }
        }




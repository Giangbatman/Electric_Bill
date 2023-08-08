package be.model;

public class Receipt {
    private String meterID;
    private String userName;
    private String address;
    private int previousNum;
    private int newNum;
    private int cash;

    public Receipt(){}

    public Receipt(String meterID, String userName, String address, int previousNum, int newNum, int cash) {
        this.meterID = meterID;
        this.userName = userName;
        this.address = address;
        this.previousNum = previousNum;
        this.newNum = newNum;
        this.cash = cash;
    }


    public String getMeterID() {
        return meterID;
    }

    public void setMeterID(String meterID) {
        this.meterID = meterID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPreviousNum() {
        return previousNum;
    }

    public void setPreviousNum(int previousNum) {
        this.previousNum = previousNum;
    }

    public int getNewNum() {
        return newNum;
    }

    public void setNewNum(int newNum) {
        this.newNum = newNum;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "meterID='" + meterID + '\'' +
                ", userName='" + userName + '\'' +
                ", address='" + address + '\'' +
                ", previousNum=" + previousNum +
                ", newNum=" + newNum +
                ", cash=" + cash +
                '}';
    }
}

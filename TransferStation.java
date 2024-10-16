import java.util.*;

public class TransferStation extends Station{
    
    ArrayList<Station> stationList = new ArrayList<Station>();

    public TransferStation(String lineColor, String name)
    {
        super(lineColor, name);
    }

    public void addPrev(Station station)
    {
        station.nextStation = this;
        stationList.add(station);
    }

    public void addNext(Station station)
    {
        station.previousStation = this;
        stationList.add(station);
    }

    public String printHelp(ArrayList<Station> stationList)
    {
        String total = "\n\tTransfers: \n";
        for (int i = 0; i < stationList.size(); i++)
        {
            total = "\t" + stationList.get(i).toString() + "\n " + total;
        }

        return total;
    }

    public String toString()
    {
        return super.toString() + printHelp(stationList);
    }
}

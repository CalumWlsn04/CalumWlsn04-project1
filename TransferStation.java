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
}

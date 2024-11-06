package database;


import java.util.HashMap;
import java.util.Map;
import entity.HoaDon;
import usecase.AddHDDatabaseBoundary;

public class HDDAOMemory implements AddHDDatabaseBoundary {
    private Map<Integer, HoaDon> mockDatabase = new HashMap<>();
    private int currentHDID = 0;

    @Override
    public int addHoadon(HoaDon hoaDon) {
        mockDatabase.put(++currentHDID, hoaDon);
        return currentHDID;
    }

    @Override
    public HoaDon findHoaDonById(int id) {
        return mockDatabase.get(id);
    }
}

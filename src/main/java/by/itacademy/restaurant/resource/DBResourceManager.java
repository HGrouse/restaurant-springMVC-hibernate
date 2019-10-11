package by.itacademy.restaurant.resource;

import org.springframework.stereotype.Service;

import java.util.ResourceBundle;

@Service
public class DBResourceManager {

    private ResourceBundle bundle;

    private DBResourceManager() {
        bundle = ResourceBundle.getBundle("db");
    }

    public String getValue(String key) {
        return bundle.getString(key);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DBResourceManager that = (DBResourceManager) o;

        return bundle != null ? bundle.equals(that.bundle) : that.bundle == null;
    }

    @Override
    public int hashCode() {
        return bundle != null ? bundle.hashCode() : 0;
    }


}

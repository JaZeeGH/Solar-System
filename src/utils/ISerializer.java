package utils;

// This has been implemented in API Class
public interface ISerializer {
    void save() throws Exception;

    void load() throws Exception;

    String fileName();
}

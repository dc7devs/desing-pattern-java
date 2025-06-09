package singleton;

/**
 * Singleton "Apressado"
 *
 * @author dc7devs
 */
public class SigletonEager {
    private static SigletonEager instancia = new SigletonEager();

    private SigletonEager() {
        super();
    }

    public static SigletonEager getInstance() {
        return instancia;
    }
}

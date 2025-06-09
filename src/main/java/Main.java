import singleton.SigletonEager;
import singleton.SingletonLazyHolder;
import strategy.ComportamentoAgressivo;
import strategy.ComportamentoDefenssivo;
import strategy.ComportamentoNormal;
import strategy.Robot;

public class Main {
    public static void main(String[] args) {
        // Singleton
        SingletonLazyHolder lazy = SingletonLazyHolder.getInstance();
        System.out.println(lazy);
        lazy = SingletonLazyHolder.getInstance();
        System.out.println(lazy);

        SigletonEager eager = SigletonEager.getInstance();
        System.out.println(eager);
        eager = SigletonEager.getInstance();
        System.out.println(eager);

        SingletonLazyHolder holder = SingletonLazyHolder.getInstance();
        System.out.println(holder);
        holder = SingletonLazyHolder.getInstance();
        System.out.println(holder);

        // Strategy
        ComportamentoNormal normal = new ComportamentoNormal();
        ComportamentoDefenssivo defenssivo = new ComportamentoDefenssivo();
        ComportamentoAgressivo agressivo = new ComportamentoAgressivo();

        Robot robo = new Robot();

        robo.setComportamento(normal);
        robo.mover();

        robo.setComportamento(agressivo);
        robo.mover();
        robo.mover();
        robo.mover();

        robo.setComportamento(defenssivo);
        robo.mover();
        robo.mover();
    }
}
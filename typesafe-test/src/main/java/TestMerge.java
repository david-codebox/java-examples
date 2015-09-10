import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/7/14
 * Time: 9:38
 */
    public class TestMerge {

    public static void main(String[] args) {
        Config custom = ConfigFactory.load("custom");
//        System.out.println(custom);
        Config config = custom.withFallback(ConfigFactory.load());
//        System.out.println(config);

        System.out.println(config.getString("git.repo.ess.comment"));
        System.out.println(config.getString("git.repo.ess.auto-close"));

        config.getStringList("git.names").forEach(System.out::println);

    }
}

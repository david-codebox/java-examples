import com.sandbox.annotation.annotation.TaskInfo;
import com.sandbox.annotation.annotation.TaskModule;
import com.sandbox.annotation.client.ITask;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.util.Set;

import static org.junit.Assert.fail;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/10/15
 * Time: 22:07
 */
public class TaskMetaDataTest {
    private static final Reflections REFLECTIONS = buildReflections("com.sandbox.annotation.client");
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testTaskInfo() throws Exception {
//        Set<Class<?>> taskClasses = REFLECTIONS.getTypesAnnotatedWith(TaskInfo.class, true);
        Set<Class<? extends ITask>> taskClasses = REFLECTIONS.getSubTypesOf(ITask.class);
        System.out.println();
        for (Class<?> taskClass : taskClasses) {
            logger.debug("TaskMetaDataTest.testTaskInfo: [{}]", taskClass.getName());
//            logger.debug("TaskMetaDataTest.testTaskInfo: isAssignableFrom: [{}]", ITask.class.isAssignableFrom(taskClass));
            TaskModule module = taskClass.getAnnotation(TaskModule.class);
            TaskInfo info = taskClass.getAnnotation(TaskInfo.class);
            int modifier = taskClass.getModifiers();
            if (Modifier.isAbstract(modifier)) {
                logger.info("TaskMetaDataTest.testTaskInfo: Class is abstract task: [{}]", taskClass.getName() );
                System.out.println("================================");
                continue;
            }

            logger.debug("TaskMetaDataTest.testTaskInfo: [{}] modifier: [{}]", taskClass.getName(), modifier);
            if (info == null || module == null) {
                logger.error("TaskMetaDataTest.testTaskInfo: Invalid task: [{}]", taskClass.getName());
            } else {

                logger.debug("TaskMetaDataTest.testTaskInfo: Module=[{}], task name= [{}]", module.module(), info.name());
            }

            System.out.println("================================");
        }

    }

    public static Reflections buildReflections(String packageName) {
        return new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(packageName))
                .setScanners(new SubTypesScanner(), new TypeAnnotationsScanner())
                .filterInputsBy(new FilterBuilder()
                        .includePackage(packageName)
                        .exclude("(?i)^.+\\.(json|conf|properties|fxml|css|xml|txt)$"))
        );
    }

    public static Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation> annoClass) {
        return REFLECTIONS.getTypesAnnotatedWith(annoClass);
    }
}

import com.sandbox.annotation.annotation.InheritedAnnotation;
import com.sandbox.annotation.client.BaseClass;
import com.sandbox.annotation.client.ChildInheritsAnnotation;
import com.sandbox.annotation.client.InheritedBase;
import com.sandbox.annotation.client.SubclassDoesNotInheritAnnotation;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/10/15
 * Time: 21:34
 */
public class AnnotationTest {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Test
    public void testAnnotations() {
        // not inherited
        Annotation[] annotations = BaseClass.class.getAnnotations();
        for (Annotation annotation : annotations) {
            logger.debug("AnnotationTest.testAnnotations: [{}]", annotation.annotationType().getName() );
        }
        assertEquals(1, annotations.length);
        annotations = SubclassDoesNotInheritAnnotation.class.getAnnotations();
        assertEquals(0, annotations.length);

        // inherited
        annotations = InheritedBase.class.getAnnotations();

        InheritedAnnotation inherited=InheritedBase.class.getAnnotation(InheritedAnnotation.class);
        logger.debug("AnnotationTest.testAnnotations: [{}]",inherited.value() );
        assertEquals(1, annotations.length);
        annotations = ChildInheritsAnnotation.class.getAnnotations();

    }
}

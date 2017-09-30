package sample;

import org.junit.After;
import org.junit.Before;
import org.testfx.framework.junit.ApplicationTest;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.fail;

public abstract class ApplicationTestTemplate extends ApplicationTest {

    public AtomicReference<Throwable> errorInUi = new AtomicReference<>(null);

    @Before
    public void resetUiError() {
        errorInUi.set(null);
    }

    @After
    public void checkUiExceptions() throws Throwable {
        if (errorInUi.get() != null) {
            fail(errorInUi.get().getMessage());
        }
    }
}

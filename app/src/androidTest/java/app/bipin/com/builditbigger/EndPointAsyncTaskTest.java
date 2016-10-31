    package app.bipin.com.builditbigger;

    import android.support.test.runner.AndroidJUnit4;

    import org.junit.Test;
    import org.junit.runner.RunWith;

    //import app.bipin.com.builditbigger.free.MainActivityFragment;

    import static org.junit.Assert.assertTrue;

/**
 * Created by BipinSutar on 31-Oct-16.
 */
@RunWith(AndroidJUnit4.class)
public class EndPointAsyncTaskTest {

    @Test
    public void testDoInBackground() throws Exception {
        app.bipin.com.builditbigger.MainActivityFragment fragment = new app.bipin.com.builditbigger.MainActivityFragment();
        fragment.testFlag = true;
        new EndpointAsyncTask().execute(fragment);
        Thread.sleep(5000);
        assertTrue("Error: Fetched Joke = " + fragment.loadedJoke, fragment.loadedJoke != null);
    }
}

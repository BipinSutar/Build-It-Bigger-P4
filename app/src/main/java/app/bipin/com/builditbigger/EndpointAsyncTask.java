package app.bipin.com.builditbigger;

import android.content.Context;
import android.os.AsyncTask;

import com.example.bipinsutar.builditbigger.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import app.bipin.com.builditbigger.free.MainActivityFragment;


/**
 * Created by BipinSutar on 30-Oct-16.
 */
public class EndpointAsyncTask extends AsyncTask<MainActivityFragment, Void, String> {

    private static MyApi myApiService = null;
    private Context context;
    private MainActivityFragment mainActivityFragment;

    @Override
    protected String doInBackground(MainActivityFragment... params) {
        mainActivityFragment = params[0];
        context = mainActivityFragment.getActivity();

        if (myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new
                    AndroidJsonFactory(), null)
                    .setRootUrl("https://joketellingapp-148018.appspot.com/_ah/api/")
            .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?>
                                                       abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();

        }
        //context = params[0];

        try {
            return myApiService.tellJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }
    @Override
    protected void onPostExecute(String result) {
        /*// Create Intent to launch JokeFactory Activity
        Intent intent = new Intent(context, DisplayJokeActivity.class);
        // Put the string in the envelope
        intent.putExtra(DisplayJokeActivity.JOKE_KEY,result);
        context.startActivity(intent);
*/
        mainActivityFragment.loadedJoke = result;
        mainActivityFragment.launchDisplayJokeActivity();
     //   Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }
}

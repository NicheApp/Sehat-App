package fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.MJ.Hack.Sehat.R;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.BuildConfig;
import mehdi.sakout.aboutpage.Element;

public class About extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=  inflater.inflate(R.layout.about, container, false);
        Element legalElement = new Element();
        legalElement.setTitle("Legal");

        Element developersElement = new Element();
        developersElement.setTitle("Nishkarsh");

        Element shareElement = new Element();
        shareElement.setTitle("elemetns");

        Element thirdPartyLicenses = new Element();
        thirdPartyLicenses.setTitle("MIT LIcence");
        AboutPage aboutPage = new AboutPage(getContext())
                .isRTL(false)
                .setImage(R.mipmap.ic_launcher)
                .setDescription("WE are team")
                .addItem(new Element("Version " + BuildConfig.VERSION_NAME, R.drawable.ic_about))
                .addGroup("Connect with us")
                .addGitHub("fossasia/open-event-organizer-android")
                .addPlayStore(getContext().getPackageName())
                .addWebsite("https://sehatcompletehealthcare.000webhostapp.com/index.html")
                //.addFacebook(getString(R.string.FACEBOOK_ID))
                //.addTwitter(getString(R.string.TWITTER_ID))
                //.addYoutube(getString(R.string.YOUTUBE_ID))
                .addItem(developersElement)
                .addItem(legalElement)
                .addItem(shareElement);

        if (BuildConfig.FLAVOR.equals("playStore")) {
            aboutPage.addItem(thirdPartyLicenses);
        }

        View aboutPageView = aboutPage.create();
        return v;
    }
}

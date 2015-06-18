package polish.easai.polishcolorapp;

import android.app.ListActivity;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

public class MainActivity extends ListActivity {
    String[] plList;
    String[] enList;
    int[] audioList;

    MediaPlayer mediaPlayer;
    int audio[] = {
            R.raw.bialy, // <item>bia?y</item>
            R.raw.czerwony, // <item>czerwony</item>
            R.raw.pomaranczowy, // <item>pomara?czowy</item>
            R.raw.zloty, // <item>z?oty</item>
            R.raw.zielony, // <item>zielony</item>
            R.raw.niebieski, // <item>niebieski</item>
            R.raw.granatowy, // <item>granatowy</item>
            R.raw.fioletowy, // <item>fioletowy</item>
            R.raw.karmazyn, // <item>karmazyn</item>
            R.raw.czarny, // <item>czarny</item>
            R.raw.rozowy, // <item>ró?owy</item>
            R.raw.szary, // <item>szary</item>
            R.raw.blekitny, // <item>b??kitny</item>
            R.raw.jasnozielony, // <item>jasnozielony</item>
            R.raw.ciemnoczerwony, // <item>ciemnoczerwony</item>
            R.raw.przezroczysty, // <item>przezroczysty</item>
            R.raw.bezowy, // <item>be?owy</item>
            R.raw.purpurowy, // <item>purpurowy</item>
            R.raw.srebrny, // <item>srebrny</item>
            R.raw.brazowy // <item>br?zowy</item>
    };

    int color[] = {
            Color.WHITE, // <item>bia?y</item>
            Color.RED, // <item>czerwony</item>
            Color.rgb(0xF4,0xA3,0x47), // <item>pomara?czowy</item>
            Color.rgb(0xFF,0xC0,0x00), // <item>z?oty</item>
            Color.GREEN, // <item>zielony</item>
            Color.BLUE, // <item>niebieski</item>
            Color.rgb(0x00,0x00,0x80), // <item>granatowy</item>
            Color.rgb(0xB8,0x03,0xFF), // <item>fioletowy</item>
            Color.rgb(0xDC,0x14,0x3C), // <item>karmazyn</item>
            Color.BLACK, // <item>czarny</item>
            Color.rgb(0xFF,0xCC,0xDD), // <item>ró?owy</item>
            Color.GRAY, // <item>szary</item>
            Color.rgb(0x00,0x7F,0xFF), // <item>b??kitny</item>
            Color.rgb(0x6E,0xBE,0x9F), // <item>jasnozielony</item>
            Color.rgb(0xAD,0x11,0x11), // <item>ciemnoczerwony</item>
            Color.WHITE, // <item>przezroczysty</item>
            Color.rgb(0xC2,0xB2,0x80), // <item>be?owy</item>
            Color.rgb(0x80,0x00,0x80), // <item>purpurowy</item>
            Color.rgb(0xC0,0xC0,0xC0), // <item>srebrny</item>
            Color.rgb(0x96,0x4B,0x00) // <item>br?zowy</item>
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] pl = getResources().getStringArray(R.array.pl);
        int nWords = pl.length;
        String[] en = getResources().getStringArray(R.array.en);
        Vector<PolishWord> wordList=new Vector<>();
        for (int i = 0; i < nWords; i++) {
            wordList.add(i,new PolishWord(pl[i], en[i], audio[i],color[i]));
        }

        Collections.sort(wordList, new Comparator<PolishWord>() {
            @Override
            public int compare(PolishWord p0, PolishWord p1) {
                return p0.pl.compareTo(p1.pl);
            }
        });

        plList = new String[nWords];
        enList = new String[nWords];
        audioList = new int[nWords];
        for (int i = 0; i < nWords; i++) {
            plList[i] = wordList.get(i).getPl();
            enList[i] = wordList.get(i).getEn();
            audioList[i] = wordList.get(i).getAudioID();
        }

        setListAdapter(new PolishAdapter(this, R.layout.activity_main, wordList));

        ListView lv = getListView();
        lv.setTextFilterEnabled(true);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String[] pl = getResources().getStringArray(R.array.en);
                Toast.makeText(getApplicationContext(),
                        enList[(int) id], Toast.LENGTH_SHORT).show();

                int audioId = audioList[(int) id];
                if (audioId != 0) {
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), audioId);
                    mediaPlayer.start();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}

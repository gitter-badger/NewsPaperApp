package com.example.android.viewpager;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.viewpager.Data.Contract;


public class FavAdapter extends CursorAdapter {

    public FavAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    private TextView newspaperNameFav;
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.fav_list, viewGroup, false);

        return rootView;
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        TextView articleFav = (TextView)view.findViewById(R.id.article);
        newspaperNameFav = (TextView)view.findViewById(R.id.newspaperName);
        TextView timeFav = (TextView)view.findViewById(R.id.time);
        TextView urlLinkFav = (TextView)view.findViewById(R.id.urlLink);
        TextView rowFav = (TextView)view.findViewById(R.id.row);

        int idPos = cursor.getColumnIndex(Contract.Entry._ID);
        String idValue = cursor.getString(idPos);

        int articlePos = cursor.getColumnIndex(Contract.Entry.COLUMN_ARTICLE);
        final String articleValue = cursor.getString(articlePos);

        int paperPos = cursor.getColumnIndex(Contract.Entry.COLUMN_PAPER);
        String paperValue = cursor.getString(paperPos);

        int timePos = cursor.getColumnIndex(Contract.Entry.COLUMN_TIME);
        final String timeValue = cursor.getString(timePos);

        int urlPos = cursor.getColumnIndex(Contract.Entry.COLUMN_LINK);
        String urlValue = cursor.getString(urlPos);

        articleFav.setText(articleValue);
        newspaperNameFav.setText(paperValue);
        timeFav.setText(timeValue);
        urlLinkFav.setText(urlValue);
        rowFav.setText(idValue);


        final int r = Integer.parseInt(rowFav.getText().toString());
        /*all news page menu */
        final ImageView opt = (ImageView)view.findViewById(R.id.opt);
        opt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                PopupMenu popup = new PopupMenu(context, opt);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.dropdown_fav, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {

                            case R.id.unsave:
                                //TextView newspaperName=(TextView) view.findViewById(R.id.newspaperName);
                                Toast.makeText(context,""+newspaperNameFav.getText().toString(),Toast.LENGTH_SHORT).show();

                                context.getContentResolver().delete(Contract.Entry.CONTENT_URI, Contract.Entry._ID +"=?", new String[]{String.valueOf(r)});
                                Toast.makeText(context, "removed from favorite", Toast.LENGTH_SHORT).show();

                                //get it by adding textview for rows
                                return true;
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });

    }
}

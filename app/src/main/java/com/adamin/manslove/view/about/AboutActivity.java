package com.adamin.manslove.view.about;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adamin.manslove.BuildConfig;
import com.adamin.manslove.R;
import com.adamin.manslove.utils.StatusBarCompact;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AboutActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.version)
    TextView version;

    @Bind(R.id.libraries)
    RecyclerView libraries;

    @Override
    @SuppressWarnings("SpellCheckingInspection")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

        toolbar.setNavigationIcon(R.drawable.ic_action_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("关于");

        version.setText(getString(R.string.version, BuildConfig.VERSION_NAME, BuildConfig.FLAVOR));
        StatusBarCompact.compat(this, Color.parseColor("#424242"));
        LibrariesAdapter adapter = new LibrariesAdapter();

        adapter.add("JakeWharton / butterknife", "https://jakewharton.github.io/butterknife/");
        adapter.add("square / okhttp", "https://square.github.io/okhttp/");
        adapter.add("square / picasso", "https://square.github.io/picasso/");
        adapter.add("google / gson", "https://github.com/google/gson");
        adapter.add("JakeWharton / nineoldandroid", "https://jakewharton.github.io/nineoldandroid/");

        libraries.setLayoutManager(new LinearLayoutManager(this));
        libraries.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void open(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    class LibrariesAdapter extends RecyclerView.Adapter<LibrariesAdapter.ViewHolder> {

        private final ArrayMap<String, String> libs = new ArrayMap<>();

        public void add(String name, String website) {
            libs.put(name, website);
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return viewType == 0
                    ? new HeaderViewHolder(getLayoutInflater().inflate(R.layout.item_header, parent, false))
                    : new ItemViewHolder(getLayoutInflater().inflate(R.layout.item_library, parent, false));
        }

        @Override
        @SuppressLint("SetTextI18n")
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (holder.getItemViewType() == 0) {
                ((HeaderViewHolder) holder).itemView.setText(
                        position == 0 ? R.string.fork_me_on_github : R.string.libraries_used);
            } else {
                ItemViewHolder itemHolder = (ItemViewHolder) holder;
                if (position == 1) {
                    itemHolder.name.setText("adamin1990 /ManLove");
                } else {
                    itemHolder.name.setText(libs.keyAt(position - 3));
                }
            }
        }

        @Override
        public int getItemViewType(int position) {
            return position == 0 || position == 2 ? 0 : 1;
        }

        @Override
        public int getItemCount() {
            return libs.size() + 3;
        }

        private void handleItemClick(int position) {
            if (position == 1) {
                open("https://github.com/adamin1990/ManLove");
            } else {
                open(libs.valueAt(position - 3));
            }
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            public ViewHolder(View itemView) {
                super(itemView);
            }

        }

        class HeaderViewHolder extends ViewHolder {

            TextView itemView;

            public HeaderViewHolder(View itemView) {
                super(itemView);
                this.itemView = (TextView) itemView;
            }

        }

        class ItemViewHolder extends ViewHolder {

            @Bind(R.id.name)
            TextView name;

            public ItemViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handleItemClick(getAdapterPosition());
                    }
                });
            }

        }

    }

}

package jiacc.myutils.activities;

import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import jiacc.myutils.R;
import jiacc.myutils.beans.Result;
import jiacc.myutils.beans.Subject;
import jiacc.myutilslib.LogUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Top250MovieActivity extends Activity {
    private int count = 0;
    private ImageView iv;
    private String testUrl = "http://api.douban.com/v2/movie/top250";
    private RecyclerView rv;
    private Result result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top250_movie);
        iv = (ImageView)findViewById(R.id.image);
        rv = (RecyclerView)findViewById(R.id.rv);
        getDatas();
    }

    public void getDatas(){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(testUrl)
                .build();
        Call call=client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonStr = response.body().string();
                LogUtils.logE("jcc",jsonStr);
                Gson gson = new Gson();
                result = gson.fromJson(jsonStr,Result.class);
                mHandler.sendEmptyMessage(MSG_WHAT_GET_RESULT_FINISHED);
            }

            @Override
            public void onFailure(Call call, IOException e) {

            }
        });
    }

    private static final int MSG_WHAT_GET_RESULT_FINISHED = 0;
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == MSG_WHAT_GET_RESULT_FINISHED){
                LogUtils.logE("jcc","msg_what = "+msg.what);
                rv.setLayoutManager(new LinearLayoutManager(Top250MovieActivity.this));
                rv.setAdapter(new Top250Adaper());
                ItemTouchHelper touchHelper = new ItemTouchHelper(itemTouchHelperCallback);
                touchHelper.attachToRecyclerView(rv);
            }
        }
    };

    class Top250Adaper extends RecyclerView.Adapter implements ItemTouchHelperAdapter{
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = View.inflate(Top250MovieActivity.this,R.layout.rv_item,null);
            return new Top250MovieActivity.Top250MovieViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof Top250MovieViewHolder){
                Subject subject = result.getSubjects().get(position);
                ((Top250MovieViewHolder)holder)
                        .name
                        .setText(position+"."+subject.getTitle());

                ((Top250MovieViewHolder)holder)
                        .year
                        .setText("  "+subject.getYear()
                        +"/"+subject.getRating().getStars());
                ((Top250MovieViewHolder)holder)
                        .ratingBar
                        .setRating((float)(subject.getRating().getAverage()));
                Picasso.with(Top250MovieActivity.this)
                        .load(subject.getImages().getMedium())
                        .into(((Top250MovieViewHolder)holder).image);
            }
        }

        @Override
        public int getItemCount() {
            return result.getCount();
        }

        @Override
        public void onItemMove(int fromPosition, int toPosition) {

        }

        @Override
        public void onItemDissmiss(int position) {

        }
    }
    class Top250MovieViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView year;
        public ImageView image;
        public RatingBar ratingBar;
        public Top250MovieViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            year = (TextView) itemView.findViewById(R.id.year);
            image = (ImageView) itemView.findViewById(R.id.image);
            ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
        }
    }
    //ItemTouchHelper的回调
    class ItemTouchHelperCallback extends ItemTouchHelper.Callback {
        public ItemTouchHelperCallback(){

        }
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            int dragFlags = ItemTouchHelper.UP|ItemTouchHelper.DOWN; //允许上线拖动
            int swipeFlags = ItemTouchHelper.LEFT; //只允许从右向左
            return makeMovementFlags(dragFlags,swipeFlags);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        }

        @Override
        public boolean isLongPressDragEnabled() {
            return true;
        }
    };

}

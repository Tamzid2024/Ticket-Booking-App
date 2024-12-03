package com.ticketbookingapp.project02.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import com.ticketbookingapp.project02.Adapter.SeatAdapter;
import com.ticketbookingapp.project02.Model.Flight;
import com.ticketbookingapp.project02.Model.Seat;
import com.ticketbookingapp.project02.R;
import com.ticketbookingapp.project02.databinding.ActivitySeatListBinding;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeatListActivity extends BaseActivity {
  private ActivitySeatListBinding binding;
  private Flight flight;
  private Double price=0.0;
  private int num=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySeatListBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        getIntentExtra();
        initSeatList();
        setVariable();
    }

    private void setVariable() {
        binding.backBtn.setOnClickListener(view -> finish());
        binding.confirmBtn.setOnClickListener(view -> {
            if (num > 0){
                flight.setPassenger(binding.nameSeatSelectedTxt.getText().toString());
                flight.setPrice(price);
                Intent intent=new Intent(SeatListActivity.this,TicketDetailActivity.class);
                intent.putExtra("flight",flight);
                startActivity(intent);
            }else {
                Toast.makeText(SeatListActivity.this, "Please select your seat", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void initSeatList() {
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,7);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
            @Override
            public int getSpanSize(int position){
                return (position % 7 == 3) ? 1 : 1;


            }

        });

        binding.seatRecyclerview.setLayoutManager(gridLayoutManager);


        List<Seat> seatList=new ArrayList<>();
        int row=0;
        int numberSeat=flight.getNumberSeat() + (flight.getNumberSeat() / 7) + 1;

        Map<Integer,String> seatAlphabetMap=new HashMap<>();
        seatAlphabetMap.put(0,"A");
        seatAlphabetMap.put(1,"B");
        seatAlphabetMap.put(2,"C");
        seatAlphabetMap.put(4,"D");
        seatAlphabetMap.put(5,"E");
        seatAlphabetMap.put(6,"F");

        for (int i=0; i < numberSeat;i++){
            if (i % 7 == 0) {
                row++;
            }
            if (i % 7 ==3){
                seatList.add(new Seat(Seat.SeatStatus.EMPTY,String.valueOf(row)));
            }else {
                String seatName = seatAlphabetMap.get(i % 7) + row;
                Seat.SeatStatus seatStatus = flight.getReservedSeats().contains(seatName) ? Seat.SeatStatus.UNAVAILABLE : Seat.SeatStatus.AVAILABLE;
                seatList.add(new Seat(seatStatus,seatName));
            }
        }
        SeatAdapter seatAdapter = new SeatAdapter(seatList, this, (selectedName, num) -> {
         binding.numberSelectedTxt.setText(num+" Seat Selected");
         binding.nameSeatSelectedTxt.setText(selectedName);
         Toast.makeText(this, selectedName, Toast.LENGTH_SHORT).show();
            DecimalFormat df=new DecimalFormat("#.##");
            price=(Double.valueOf(df.format(num*flight.getPrice())));
            this.num=num;
            binding.priceTxt.setText("$"+price);
        });

        binding.seatRecyclerview.setAdapter(seatAdapter);
        binding.seatRecyclerview.setNestedScrollingEnabled(false);
    }

    private void getIntentExtra() {
        flight= (Flight) getIntent().getSerializableExtra("flight");
    }
}
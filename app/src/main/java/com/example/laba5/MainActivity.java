package com.example.laba5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class MainActivity extends AppCompatActivity implements NewOrderDialog.OnNewOrderListener {

    //курьер
    Courier courier = new Courier("Андроид Студио Ненавижувич", "12345678901");

    //посылки
    SmallPackage clock = new SmallPackage("30*40*50", false, "Магнитогорск", "Сочи");
    SmallPackage keys = new SmallPackage("10*10*40", true, "Челябинск", "Москва");
    Documents document = new Documents("Челябинск", "Москва");
    BigPackage playstation = new BigPackage("100*70*80", false, 10, "Москва", "Санкт-Петербург");
    BigPackage chair = new BigPackage("100*100*100", false, 5, "Хабаровск", "Новокузнецк");
    SmallPackage tshirt = new SmallPackage("10*10*2", false, "Москва", "Санкт-Петербург");
    SmallPackage sneakers = new SmallPackage("30*20*10", false, "Челябинск", "Москва");
    SmallPackage flashDrive = new SmallPackage("5*5*1", true, "Магнитогорск", "Сочи");
    BigPackage tv = new BigPackage("100*70*10", false, 20, "Уфа", "Челябинск");
    BigPackage laptop = new BigPackage("40*30*5", false, 3, "Челябинск", "Уфа");
    BigPackage phone = new BigPackage("15*8*1", false, 1, "Магнитогорск", "Сочи");
    BigPackage battery = new BigPackage("20*20*10", true, 2, "Сочи", "Москва");

    //компании
    Company yandex = new Company("Yandex", "Москва, ул. Льва Толстого, 16");
    Company google = new Company("Google", "America");
    Company ozon = new Company("Ozon", "Москва, Пресненская Набережная 10");
    Company avito = new Company("Avito", "Москва, Лесная улица, 7, А");
    Company amazon = new Company("Amazon", "Seattle, WA, USA");
    Company apple = new Company("Apple", "Cupertino, CA, USA");
    Company ebay = new Company("Ebay", "San Jose, CA, USA");

    //заказы
    Order order1 = new Order(yandex, keys, keys.getFrom(), keys.getTo(), 1200);
    Order order2 = new Order(ozon, clock, clock.getFrom(), clock.getTo(), 1500);
    Order order3 = new Order(google, document, document.getFrom(), document.getTo(), 1600);
    Order order4 = new Order(google, playstation, playstation.getFrom(), playstation.getTo(), 3500);
    Order order5 = new Order(avito, chair, chair.getFrom(), chair.getTo(), 5000);
    Order order6 = new Order(amazon, tshirt, tshirt.getFrom(), tshirt.getTo(), 800);
    Order order7 = new Order(apple, sneakers, sneakers.getFrom(), sneakers.getTo(), 1800);
    Order order8 = new Order(ebay, flashDrive, flashDrive.getFrom(), flashDrive.getTo(), 300);
    Order order9 = new Order(amazon, tv, tv.getFrom(), tv.getTo(), 4500);
    Order order10 = new Order(apple, laptop, laptop.getFrom(), laptop.getTo(), 6000);
    Order order11 = new Order(ebay, phone, phone.getFrom(), phone.getTo(), 4000);
    Order order12 = new Order(amazon, battery, battery.getFrom(), battery.getTo(), 1500);

    private ListView listView;
    private OrderAdapter adapter;

    private List<Order> selectedOrders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView courier_name = findViewById(R.id.courier_name);
        courier_name.setText(courier.getFIO());

        courier.addOrder(new Order(new Company("Поколение Альфа", "Электролитный пр., 17с1"), new BigPackage("40*40*40", true, 15, "Москва", "Москва"), "Б. Черемушкинская, 1", "Электролитный пр., 17с1", 1400));
        courier.addOrder(new Order(new Company("ТРЦ РИО", "Б. Черемушкинская, 1"), new SmallPackage("10*10*5", false, "Москва", "Москва"), "Борисовские пруды, 26с1", "Б. Черемушкинская, 1", 500));
        courier.addOrder(new Order(new Company("Охрана Президента", "Красная площадь, 1"), new BigPackage("30*40*50", true, 50, "Москва", "Москва"), "Красная Площадь, 1", "Президентские дачи, 17", 60000));


        listView = findViewById(R.id.listView);
        registerForContextMenu(listView);

        adapter = new OrderAdapter(this, courier.getOrders());


        listView.setAdapter(adapter);

        Button btn_ok = findViewById(R.id.button_ok);
        Button btn_clear = findViewById(R.id.button_clear);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double result = 0;
                for (int i = 0; i < adapter.getOrders().size(); i++) {
                    if (adapter.getOrders().get(i).isSelected()) {
                        result += Double.parseDouble(adapter.getOrders().get(i).getCost());
                        selectedOrders.add(adapter.getOrders().get(i));
                    }
                }
                // Remove selected orders from courier's orders
                courier.getOrders().removeAll(selectedOrders);
                // Notify adapter to update the list view
                adapter.notifyDataSetChanged();
                showInfo(result);
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < adapter.getOrders().size(); i++) {
                    adapter.getOrders().get(i).setSelected(false);
                }
                adapter.notifyDataSetChanged();

            }
        });

        Button btn_show = findViewById(R.id.button_show);
        registerForContextMenu(btn_show);
        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openContextMenu(v);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        Collection<List<Order>> orders = selectedOrders.stream().collect(
                        Collectors.groupingBy(it -> Arrays.asList(it.getAddrFrom(), it.getAddrTo())))
                .values();
        for (List<Order> order : orders) {
            String menuTitle = "";
            int count = order.size();

            if(count > 1) {
                menuTitle = "Кол: " + count+" ";
            }
            Order oneOrder = order.get(0);
            menuTitle += "Тип: " + order.stream().map(it -> it.getPack().getType()).collect(Collectors.joining(",")) + ";\n" + oneOrder.getAddrFrom().substring(0,3) + " -> " + oneOrder.getAddrTo().substring(0,3) + "\nЦена: ";
            if(count > 1)
                menuTitle += String.valueOf(order.stream().mapToDouble(or -> Double.parseDouble(or.getCost())).sum());
            else menuTitle += oneOrder.getCost();
            menu.add(Menu.NONE, Menu.NONE, Menu.NONE, menuTitle);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getItemId() == R.id.add_to_menu) {
            // вы можете добавить заказ в список заказов курьера и удалить его из доступных заказов
            Order selectedOrder = adapter.getOrders().get(info.position);
            courier.addOrder(selectedOrder);
            adapter.getOrders().remove(info.position);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Order added to the menu", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onNewOrderCreated(Order newOrder) {
        courier.addOrder(newOrder);
        adapter.notifyDataSetChanged();
    }

    private void showInfo(double cost) {
        Toast.makeText(this, "Общая стоимость: " + cost, Toast.LENGTH_LONG).show();
    }

}
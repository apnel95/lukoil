package com.example.lukoil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lukoil.entity.Act_doc;
import com.example.lukoil.entity.Act_pump;
import com.example.lukoil.entity.Act_trub;
import com.example.lukoil.entity.Employee;
import com.example.lukoil.entity.Event_date_time;
import com.example.lukoil.entity.Remark;
import com.example.lukoil.entity.Work;
import com.example.lukoil.entity.comparation.Act_trub_comparatot;

import org.jetbrains.annotations.TestOnly;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends GeneralClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        acts_pump = new ArrayList<Act_pump>();
        actsTrub = new ArrayList<Act_trub>();
        acts_doc = new ArrayList<Act_doc>();

        Activity activity = new Activity(0, context, R.layout.home, findViewById(R.id.layoutBlock), new ArrayList<View>(), findViewById(R.id.layout_menu), "Главная");

        setContentView(activity.idLayout);

        toUpdateDir(new View(activity.getContext()));

        allEds = activity.workplaceElements;
        linear = activity.workplace;

        getActsTrub();
        getActsPump();
        getActsDoc();

        onStartThis();

        uppTextName.setText(activity.getTopTitle());
    }

    @TestOnly
    private void createTestData() {

        ArrayList<Event_date_time> works = new ArrayList<Event_date_time>();
        works.add(new Event_date_time(1, 1, 2, new Date((2002-1900), 10, 23, 4, 12)));
        works.add(new Event_date_time(2, 0, 1, new Date((2023-1900), 5, 10)));
        works.add(new Event_date_time(3, 0, 0, new Date((2023-1900), 4, 1, 18, 56)));
        works.add(new Event_date_time(4, 0, 5, new Date()));
        actsTrub.add(new Act_trub(1, 1, 10, 12, 15, 40, 1, 56, 5, 12, 1, 1, 24, works ));
        ArrayList<Event_date_time> works1 = new ArrayList<Event_date_time>();
        works1.add(new Event_date_time(1, 1, 2, new Date((2002-1900), 10, 23)));
        works1.add(new Event_date_time(2, 0, 1, new Date((2023-1900), 5, 10)));
        works1.add(new Event_date_time(3, 0, 0, new Date((2023-1900), 4, 24, 23, 49)));
        works1.add(new Event_date_time(4, 0, 5, new Date()));
        actsTrub.add(new Act_trub(8, works1 ));
        ArrayList<Event_date_time> works2 = new ArrayList<Event_date_time>();
        works2.add(new Event_date_time(1, 1, 2, new Date((2002-1900), 10, 23)));
        works2.add(new Event_date_time(2, 0, 1, new Date((2023-1900), 5, 5)));
        works2.add(new Event_date_time(3, 0, 0, new Date()));
        works2.add(new Event_date_time(4, 0, 5, new Date()));
        actsTrub.add(new Act_trub(4, works2 ));
        ArrayList<Event_date_time> works3 = new ArrayList<Event_date_time>();
        works3.add(new Event_date_time(1, 1, 2, new Date((2002-1900), 10, 23)));
        works3.add(new Event_date_time(2, 0, 1, new Date((2023-1900), 5, 5)));
        works3.add(new Event_date_time(3, 0, 0, new Date((2023-1900), 3, 10, 0, 3)));
        works3.add(new Event_date_time(4, 0, 5, new Date()));
        actsTrub.add(new Act_trub(5, works3));

        ArrayList<Integer> list_reade = new ArrayList<Integer>();
        list_reade.add(1);
        list_reade.add(2);

        ArrayList<Remark> remarks1 = new ArrayList<Remark>();
        ArrayList<Remark> remarks2 = new ArrayList<Remark>();
        remarks1.add(new Remark(1, 1, "Ошибка В1346"));
        remarks1.add(new Remark(2, 2, "Ошибка В166"));
        remarks1.add(new Remark(2, 2, "Ошибка A1"));
        remarks2.add(new Remark(2, 2, "Ошибка В161"));

        ArrayList<Work> work12 = new ArrayList<Work>();
        work12.add(new Work(1, 1, "Работа Вх46", "Первый"));
        work12.add(new Work(2, 2, "Работа Вх6", "Первый3"));

        acts_doc.add(new Act_doc(1, 0, 1, 1, 1, works2, new ArrayList<Remark>(), new ArrayList<Work>(), "Михалил"));
        acts_doc.add(new Act_doc(2, 4, 1, 1, 0, works1, remarks1, work12, "Свет"));
        acts_doc.add(new Act_doc(2, 0, 4, 1, 0, works3, remarks2, new ArrayList<Work>(), "Щило"));

        acts_pump.add(new Act_pump(1, 1,1, 1, 1, "aaa", works, list_reade));
        acts_pump.add(new Act_pump(2, 2,5, 3, 0, "DFAfakfioajf", works2, list_reade));
    }

    protected void onStartThis() {
        super.onStartHome(idForm);
        drawActs(actsTrub, acts_pump, acts_doc);
    }
    public void drawActs(ArrayList<Act_trub> acts_trub, ArrayList<Act_pump> acts_pump, ArrayList<Act_doc> acts_doc) {
        Collections.sort(acts_trub, new Act_trub_comparatot());
        Date nowDate = new Date(0, 0, 1);
        int cnt = 0;
        if (acts_trub.size() > 0) {
            for (Act_trub act:acts_trub) if(act.getDate_time_stop() == null) act.setDate_time_stop(new Date());
            final View view2 = getLayoutInflater().inflate(R.layout.custom_block_type_name, null);
            TextView textName1 = (TextView) view2.findViewById(R.id.textName);
            textName1.setText("Трубопровод");
            allEds.add(view2);
            linear.addView(view2);
            for (Act_trub act : acts_trub) {
                if ((act.getId_status() == STATUS_JOB)) {
                    if (trim(act.getDate_time_stop()).equals(trim(nowDate))) {
                    } else {
                        final View view = getLayoutInflater().inflate(R.layout.custom_block_date, null);
                        TextView textDate = (TextView) view.findViewById(R.id.dateText);
                        nowDate = act.getDate_time_stop();
                        textDate.setText(DateToText(nowDate));
                        allEds.add(view);
                        linear.addView(view);
                    }
                    nowDate = act.getDate_time_stop();
                    final View view1 = getLayoutInflater().inflate(R.layout.custom_block_name, null);
                    TextView textTime = (TextView) view1.findViewById(R.id.textTime);
                    TextView textName = (TextView) view1.findViewById(R.id.textName);
                    ImageView status = (ImageView) view1.findViewById(R.id.status);
                    view1.setTag((int) 1000 + act.getId());
                    textName.setText(act.getName(trubs)+"");
                    SimpleDateFormat formatForDate = new SimpleDateFormat("HH:mm");
                    textTime.setText(formatForDate.format(nowDate));
                    allEds.add(view1);
                    linear.addView(view1);
                    cnt++;
                }
            }
        }
        cnt=0;
        if (acts_pump.size() > 0) {
            for (Act_pump act:acts_pump) if(act.getDate_time_stop() == null) act.setDate_time_stop(new Date());
            final View view2 = getLayoutInflater().inflate(R.layout.custom_block_type_name, null);
            TextView textName1 = (TextView) view2.findViewById(R.id.textName);
            textName1.setText("Насосы");
            allEds.add(view2);
            linear.addView(view2);
            for (Act_pump act : acts_pump) {
                if ((act.getId_status() == STATUS_JOB)) {
                    if(act.getDate_time_stop() == null) act.setDate_time_stop(new Date());
                    if(nowDate == null) nowDate = new Date();
                    if (trim(act.getDate_time_stop()).equals(trim(nowDate))) {
                    } else {
                        final View view = getLayoutInflater().inflate(R.layout.custom_block_date, null);
                        TextView textDate = (TextView) view.findViewById(R.id.dateText);
                        nowDate = act.getDate_time_stop();
                        textDate.setText(DateToText(nowDate));
                        allEds.add(view);
                        linear.addView(view);
                    }
                    nowDate = act.getDate_time_stop();
                    final View view1 = getLayoutInflater().inflate(R.layout.custom_block_name, null);
                    TextView textTime = (TextView) view1.findViewById(R.id.textTime);
                    TextView textName = (TextView) view1.findViewById(R.id.textName);
                    ImageView status = (ImageView) view1.findViewById(R.id.status);
                    view1.setTag((int) 2000 + act.getId());
                    textName.setText(act.getName(positions)+"");
                    SimpleDateFormat formatForDate = new SimpleDateFormat("HH:mm");
                    textTime.setText(formatForDate.format(nowDate));
                    allEds.add(view1);
                    linear.addView(view1);
                    cnt++;
                }
            }
        }
        cnt=0;
        if (acts_doc.size() > 0) {
            for (Act_doc act:acts_doc) if(act.getDate_time_stop() == null) act.setDate_time_stop(new Date());
            final View view2 = getLayoutInflater().inflate(R.layout.custom_block_type_name, null);
            TextView textName1 = (TextView) view2.findViewById(R.id.textName);
            textName1.setText("Предписания");
            allEds.add(view2);
            linear.addView(view2);
            for (Act_doc act : acts_doc) {
                if ((act.getId_status() == STATUS_JOB)) {
                    if (trim(act.getDate_time_stop()).equals(trim(nowDate))) {
                    } else {
                        final View view = getLayoutInflater().inflate(R.layout.custom_block_date, null);
                        TextView textDate = (TextView) view.findViewById(R.id.dateText);
                        nowDate = act.getDate_time_stop();
                        textDate.setText(DateToText(nowDate));
                        allEds.add(view);
                        linear.addView(view);
                    }
                    nowDate = act.getDate_time_stop();
                    final View view1 = getLayoutInflater().inflate(R.layout.custom_block_name, null);
                    TextView textTime = (TextView) view1.findViewById(R.id.textTime);
                    TextView textName = (TextView) view1.findViewById(R.id.textName);
                    ImageView status = (ImageView) view1.findViewById(R.id.status);
                    view1.setTag((int) 3000 + act.getId());
                    String str = "";
                    if (trubs != null) for (Employee emp: employees) if(emp.getId() == act.getId_employee()){str = emp.getFIO(); break;}
                    textName.setText("Выдано: " + str);
                    SimpleDateFormat formatForDate = new SimpleDateFormat("HH:mm");
                    textTime.setText(formatForDate.format(nowDate));
                    allEds.add(view1);
                    linear.addView(view1);
                    cnt++;
                }
            }
        }
    }
    public void getActsTrub() {
        Thread thread = new Thread(() -> {
            System.out.println("Waiting for connection");
            ObjectOutputStream outgetboard;
            Socket clientSocket, upClientSocket;
            try {
                clientSocket = new Socket(HOST, PORT);
                OutputStream outToServer = clientSocket.getOutputStream();
                outgetboard = new ObjectOutputStream(outToServer);

                upClientSocket = new Socket(HOST, upPORT);
                OutputStream outToUpdateServer = upClientSocket.getOutputStream();
                ObjectOutputStream outUpdate = new ObjectOutputStream(outToUpdateServer);

                System.out.println("Client connected to socket");
                Runtime.getRuntime().addShutdownHook(new Thread( () -> {
                    try {
                        HashMap.Entry<String, Object> output2 = new AbstractMap.SimpleEntry<>("QUIT", null);
                        outgetboard.writeObject(output2);
                        clientSocket.close();
                        outUpdate.writeObject(output2);
                        upClientSocket.close();
                        System.out.println("Closing the connection");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }));
            } catch (UnknownHostException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            HashMap.Entry<String, Object> output;
            output = new AbstractMap.SimpleEntry<>("GETACTSTRUB", 0);
            try {
                outgetboard.writeObject(output);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            InputStream inputStream;
            try {
                inputStream = clientSocket.getInputStream();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ObjectInputStream in;
            try {
                in = new ObjectInputStream(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                actsTrub = (ArrayList<Act_trub>) in.readObject();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        });
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            Log.d("Clown", e.toString());
        }
    }
    private void getActsPump() {
        Thread thread = new Thread(() -> {
            System.out.println("Waiting for connection");
            ObjectOutputStream outgetboard;
            Socket clientSocket, upClientSocket;
            try {
                clientSocket = new Socket(HOST, PORT);
                OutputStream outToServer = clientSocket.getOutputStream();
                outgetboard = new ObjectOutputStream(outToServer);

                upClientSocket = new Socket(HOST, upPORT);
                OutputStream outToUpdateServer = upClientSocket.getOutputStream();
                ObjectOutputStream outUpdate = new ObjectOutputStream(outToUpdateServer);

                System.out.println("Client connected to socket");
                Runtime.getRuntime().addShutdownHook(new Thread( () -> {
                    try {
                        HashMap.Entry<String, Object> output2 = new AbstractMap.SimpleEntry<>("QUIT", null);
                        outgetboard.writeObject(output2);
                        clientSocket.close();
                        outUpdate.writeObject(output2);
                        upClientSocket.close();
                        System.out.println("Closing the connection");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }));
            } catch (UnknownHostException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            HashMap.Entry<String, Object> output;
            output = new AbstractMap.SimpleEntry<>("GETACTSPUMP", 0);
            try {
                outgetboard.writeObject(output);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            InputStream inputStream;
            try {
                inputStream = clientSocket.getInputStream();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ObjectInputStream in;
            try {
                in = new ObjectInputStream(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                acts_pump = (ArrayList<Act_pump>) in.readObject();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        });
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            Log.d("Clown", e.toString());
        }
    }
    private void getActsDoc() {
        Thread thread = new Thread(() -> {
            System.out.println("Waiting for connection");
            ObjectOutputStream outgetboard;
            Socket clientSocket, upClientSocket;
            try {
                clientSocket = new Socket(HOST, PORT);
                OutputStream outToServer = clientSocket.getOutputStream();
                outgetboard = new ObjectOutputStream(outToServer);

                upClientSocket = new Socket(HOST, upPORT);
                OutputStream outToUpdateServer = upClientSocket.getOutputStream();
                ObjectOutputStream outUpdate = new ObjectOutputStream(outToUpdateServer);

                System.out.println("Client connected to socket");
                Runtime.getRuntime().addShutdownHook(new Thread( () -> {
                    try {
                        HashMap.Entry<String, Object> output2 = new AbstractMap.SimpleEntry<>("QUIT", null);
                        outgetboard.writeObject(output2);
                        clientSocket.close();
                        outUpdate.writeObject(output2);
                        upClientSocket.close();
                        System.out.println("Closing the connection");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }));
            } catch (UnknownHostException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            HashMap.Entry<String, Object> output;
            output = new AbstractMap.SimpleEntry<>("GETACTSDOC", 0);
            try {
                outgetboard.writeObject(output);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            InputStream inputStream;
            try {
                inputStream = clientSocket.getInputStream();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ObjectInputStream in;
            try {
                in = new ObjectInputStream(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                acts_doc = (ArrayList<Act_doc>) in.readObject();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        });
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            Log.d("Clown", e.toString());
        }
    }
}
package com.example.lukoil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lukoil.entity.DocAct;
import com.example.lukoil.entity.PumpAct;
import com.example.lukoil.entity.PipeAct;
import com.example.lukoil.entity.departmentObject;
import com.example.lukoil.entity.Dir;
import com.example.lukoil.entity.Employee;
import com.example.lukoil.entity.Event_date_time;
import com.example.lukoil.entity.Remark;
import com.example.lukoil.entity.Work;
import com.example.lukoil.entity.comparation.ActDocComparatot;
import com.example.lukoil.entity.comparation.ActPumpComparatot;
import com.example.lukoil.entity.comparation.ActPipeComparatot;

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

public class MainActivity extends ActivitySet {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Activity activity = new Activity(ID_ACTIVITY_HOME, context, R.layout.home, findViewById(R.id.layoutBlock), new ArrayList<View>(), findViewById(R.id.layout_menu), "Главная");
        initializationMainActivity(activity);

        updateDirsAndActs();
    }

    private void updateDirsAndActs() {
        if (AUTO_UPDATE_DIRS) getDirs();
        getActs();
        CorrectionDataActs();
        drawActs();
    }

    private void CorrectionDataActs() {
        for (PipeAct act:pipeActs) if(act.getDateTimeStop() == null) act.setDateTimeStop(new Date());

        sortActs();
    }

    private void sortActs() {
        Collections.sort(pipeActs, new ActPipeComparatot());
        Collections.sort(pumpActs, new ActPumpComparatot());
        Collections.sort(docActs, new ActDocComparatot());
    }

    private void getActs() {
        ActsOnServer actsOnServer = new ActsOnServer();
        actsOnServer.getPipeActsMin();
        pipeActs = actsOnServer.getActs();
        actsOnServer.getPumpActsMin();
        pumpActs = actsOnServer.getActs();
        actsOnServer.getDocActsMin();
        docActs = actsOnServer.getActs();
    }

    private void drawActs() {
        drawPipeActs();
        drawPumpActs();
        drawDocActs();
    }

    private void drawPipeActs() {
        Date dateStopInLastAct = new Date(100000);

        drawNewFieldForAct(new Field(R.layout.custom_block_type_name, R.id.textName, "Трубопровод"));

        for (PipeAct act : pipeActs) {
            CheckAndDrawFields(act, dateStopInLastAct);
            dateStopInLastAct = act.getDateTimeStop();
        }

    }

    private void CheckAndDrawFields(PipeAct act, Date dateStopInLastAct) {
        SimpleDateFormat formatForDate = new SimpleDateFormat("HH:mm");
        if ((act.getId_status() == ACT_STATUS_JOB)) {
            if (isDatesNotEquivalent(act.getDateTimeStop(), dateStopInLastAct)) {
                drawNewFieldForAct(new Field(R.layout.custom_block_date, R.id.dateText, DateToText(act.getDateTimeStop())));
            }
            drawNewAct(new FieldAct(R.layout.custom_block_name, R.id.textName, act.getName(pipes), R.id.textTime, formatForDate.format(act.getDateTimeStop()), R.id.status, 1000 + act.getId()));
        }
    }
    private void CheckAndDrawFields(PumpAct act, Date dateStopInLastAct) {
        SimpleDateFormat formatForDate = new SimpleDateFormat("HH:mm");
        if ((act.getId_status() == ACT_STATUS_JOB)) {
            if (isDatesNotEquivalent(act.getDateTimeStop(), dateStopInLastAct)) {
                drawNewFieldForAct(new Field(R.layout.custom_block_date, R.id.dateText, DateToText(act.getDateTimeStop())));
            }
            drawNewAct(new FieldAct(R.layout.custom_block_name, R.id.textName, act.getName(pumps), R.id.textTime, formatForDate.format(act.getDateTimeStop()), R.id.status, 2000 + act.getId()));
        }
    }
    private void CheckAndDrawFields(DocAct act, Date dateStopInLastAct) {
        SimpleDateFormat formatForDate = new SimpleDateFormat("HH:mm");
        if ((act.getId_status() == ACT_STATUS_JOB)) {
            if (isDatesNotEquivalent(act.getDateTimeStop(), dateStopInLastAct)) {
                drawNewFieldForAct(new Field(R.layout.custom_block_date, R.id.dateText, DateToText(act.getDateTimeStop())));
            }
            String str = getNameById(employees, act.getId_employee());
            drawNewAct(new FieldAct(R.layout.custom_block_name, R.id.textName, ("Выдано: " + str), R.id.textTime, formatForDate.format(act.getDateTimeStop()), R.id.status, 2000 + act.getId()));
        }
    }

    private String getNameById(ArrayList<Employee> employees, int idEmployee) {
        for (Employee emp: employees)
            if(emp.getId() == idEmployee)
                return emp.getFIO();
        System.out.println("Не найден id "+ idEmployee + " в списке employees");
        return "";
    }

    private void drawNewAct(FieldAct fieldAct) {
        final View view = getLayoutInflater().inflate(fieldAct.getIdView(), null);
        TextView textName = view.findViewById(fieldAct.getIdTextView());
        TextView textSecond = view.findViewById(fieldAct.getIdSecondTextView());
        ImageView status = view.findViewById(fieldAct.getIdStatus());
        view.setTag(fieldAct.getTag());
        textName.setText(fieldAct.getTextForTextView()+"");
        textSecond.setText(fieldAct.getTextForSecondTextView());
        workplaceElements.add(view);
        workplace.addView(view);
    }

    private boolean isDatesNotEquivalent(Date dateTimeStop, Date dateStopLastAct) {
        return (!(trim(dateTimeStop).equals(trim(dateStopLastAct))));
    }

    private void drawNewFieldForAct(Field field) {
        final View view = getLayoutInflater().inflate(field.getIdView(), null);
        TextView textView = view.findViewById(field.getIdTextView());
        textView.setText(field.getTextForTextView());
        workplaceElements.add(view);
        workplace.addView(view);
    }

    private void drawPumpActs() {
        Date dateStopInLastAct = new Date(100000);

        drawNewFieldForAct(new Field(R.layout.custom_block_type_name, R.id.textName, "Насосы"));

        for (PumpAct act : pumpActs) {
            CheckAndDrawFields(act, dateStopInLastAct);
            dateStopInLastAct = act.getDateTimeStop();
        }
    }

    private void drawDocActs() {
        Date dateStopInLastAct = new Date(100000);

        drawNewFieldForAct(new Field(R.layout.custom_block_type_name, R.id.textName, "Насосы"));

        for (DocAct act : docActs) {
            CheckAndDrawFields(act, dateStopInLastAct);
            dateStopInLastAct = act.getDateTimeStop();
        }
    }

    @TestOnly
    private void createTestData() {

        ArrayList<Event_date_time> works = new ArrayList<Event_date_time>();
        works.add(new Event_date_time(1, 1, 2, new Date((2002-1900), 10, 23, 4, 12)));
        works.add(new Event_date_time(2, 0, 1, new Date((2023-1900), 5, 10)));
        works.add(new Event_date_time(3, 0, 0, new Date((2023-1900), 4, 1, 18, 56)));
        works.add(new Event_date_time(4, 0, 5, new Date()));
        pipeActs.add(new PipeAct(1, 1, 10, 12, 15, 40, 1, 56, 5, 12, 1, 1, 24, works ));
        ArrayList<Event_date_time> works1 = new ArrayList<Event_date_time>();
        works1.add(new Event_date_time(1, 1, 2, new Date((2002-1900), 10, 23)));
        works1.add(new Event_date_time(2, 0, 1, new Date((2023-1900), 5, 10)));
        works1.add(new Event_date_time(3, 0, 0, new Date((2023-1900), 4, 24, 23, 49)));
        works1.add(new Event_date_time(4, 0, 5, new Date()));
        pipeActs.add(new PipeAct(8, works1 ));
        ArrayList<Event_date_time> works2 = new ArrayList<Event_date_time>();
        works2.add(new Event_date_time(1, 1, 2, new Date((2002-1900), 10, 23)));
        works2.add(new Event_date_time(2, 0, 1, new Date((2023-1900), 5, 5)));
        works2.add(new Event_date_time(3, 0, 0, new Date()));
        works2.add(new Event_date_time(4, 0, 5, new Date()));
        pipeActs.add(new PipeAct(4, works2 ));
        ArrayList<Event_date_time> works3 = new ArrayList<Event_date_time>();
        works3.add(new Event_date_time(1, 1, 2, new Date((2002-1900), 10, 23)));
        works3.add(new Event_date_time(2, 0, 1, new Date((2023-1900), 5, 5)));
        works3.add(new Event_date_time(3, 0, 0, new Date((2023-1900), 3, 10, 0, 3)));
        works3.add(new Event_date_time(4, 0, 5, new Date()));
        pipeActs.add(new PipeAct(5, works3));

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

        docActs.add(new DocAct(1, 0, 1, 1, 1, works2, new ArrayList<Remark>(), new ArrayList<Work>(), "Михалил"));
        docActs.add(new DocAct(2, 4, 1, 1, 0, works1, remarks1, work12, "Свет"));
        docActs.add(new DocAct(2, 0, 4, 1, 0, works3, remarks2, new ArrayList<Work>(), "Щило"));

        pumpActs.add(new PumpAct(1, 1,1, 1, 1, "aaa", works, list_reade));
        pumpActs.add(new PumpAct(2, 2,5, 3, 0, "DFAfakfioajf", works2, list_reade));
    }
    public void drawActs(ArrayList<PipeAct> acts_trub, ArrayList<PumpAct> acts_pump, ArrayList<DocAct> acts_doc) {
        Collections.sort(acts_trub, new ActPipeComparatot());
        Date nowDate = new Date(0, 0, 1);
        int cnt = 0;
        if (acts_trub.size() > 0) {
            for (PipeAct act:acts_trub) if(act.getDateTimeStop() == null) act.setDateTimeStop(new Date());
            final View view2 = getLayoutInflater().inflate(R.layout.custom_block_type_name, null);
            TextView textName1 = (TextView) view2.findViewById(R.id.textName);
            textName1.setText("Трубопровод");
            workplaceElements.add(view2);
            workplace.addView(view2);
            for (PipeAct act : acts_trub) {
                if ((act.getId_status() == ACT_STATUS_JOB)) {
                    if (trim(act.getDateTimeStop()).equals(trim(nowDate))) {
                    } else {
                        final View view = getLayoutInflater().inflate(R.layout.custom_block_date, null);
                        TextView textDate = (TextView) view.findViewById(R.id.dateText);
                        nowDate = act.getDateTimeStop();
                        textDate.setText(DateToText(nowDate));
                        workplaceElements.add(view);
                        workplace.addView(view);
                    }
                    nowDate = act.getDateTimeStop();
                    final View view1 = getLayoutInflater().inflate(R.layout.custom_block_name, null);
                    TextView textTime = (TextView) view1.findViewById(R.id.textTime);
                    TextView textName = (TextView) view1.findViewById(R.id.textName);
                    ImageView status = (ImageView) view1.findViewById(R.id.status);
                    view1.setTag((int) 1000 + act.getId());
                    textName.setText(act.getName(pipes)+"");
                    SimpleDateFormat formatForDate = new SimpleDateFormat("HH:mm");
                    textTime.setText(formatForDate.format(nowDate));
                    workplaceElements.add(view1);
                    workplace.addView(view1);
                    cnt++;
                }
            }
        }
        cnt=0;
        if (acts_pump.size() > 0) {
            for (PumpAct act:acts_pump) if(act.getDate_time_stop() == null) act.setDate_time_stop(new Date());
            final View view2 = getLayoutInflater().inflate(R.layout.custom_block_type_name, null);
            TextView textName1 = (TextView) view2.findViewById(R.id.textName);
            textName1.setText("Насосы");
            workplaceElements.add(view2);
            workplace.addView(view2);
            for (PumpAct act : acts_pump) {
                if ((act.getId_status() == ACT_STATUS_JOB)) {
                    if(act.getDate_time_stop() == null) act.setDate_time_stop(new Date());
                    if(nowDate == null) nowDate = new Date();
                    if (trim(act.getDate_time_stop()).equals(trim(nowDate))) {
                    } else {
                        final View view = getLayoutInflater().inflate(R.layout.custom_block_date, null);
                        TextView textDate = (TextView) view.findViewById(R.id.dateText);
                        nowDate = act.getDate_time_stop();
                        textDate.setText(DateToText(nowDate));
                        workplaceElements.add(view);
                        workplace.addView(view);
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
                    workplaceElements.add(view1);
                    workplace.addView(view1);
                    cnt++;
                }
            }
        }
        cnt=0;
        if (acts_doc.size() > 0) {
            for (DocAct act:acts_doc) if(act.getDate_time_stop() == null) act.setDate_time_stop(new Date());
            final View view2 = getLayoutInflater().inflate(R.layout.custom_block_type_name, null);
            TextView textName1 = (TextView) view2.findViewById(R.id.textName);
            textName1.setText("Предписания");
            workplaceElements.add(view2);
            workplace.addView(view2);
            for (DocAct act : acts_doc) {
                if ((act.getId_status() == ACT_STATUS_JOB)) {
                    if (trim(act.getDate_time_stop()).equals(trim(nowDate))) {
                    } else {
                        final View view = getLayoutInflater().inflate(R.layout.custom_block_date, null);
                        TextView textDate = (TextView) view.findViewById(R.id.dateText);
                        nowDate = act.getDate_time_stop();
                        textDate.setText(DateToText(nowDate));
                        workplaceElements.add(view);
                        workplace.addView(view);
                    }
                    nowDate = act.getDate_time_stop();
                    final View view1 = getLayoutInflater().inflate(R.layout.custom_block_name, null);
                    TextView textTime = (TextView) view1.findViewById(R.id.textTime);
                    TextView textName = (TextView) view1.findViewById(R.id.textName);
                    ImageView status = (ImageView) view1.findViewById(R.id.status);
                    view1.setTag((int) 3000 + act.getId());
                    String str = "";
                    if (pipes != null) for (Employee emp: employees) if(emp.getId() == act.getId_employee()){str = emp.getFIO(); break;}
                    textName.setText("Выдано: " + str);
                    SimpleDateFormat formatForDate = new SimpleDateFormat("HH:mm");
                    textTime.setText(formatForDate.format(nowDate));
                    workplaceElements.add(view1);
                    workplace.addView(view1);
                    cnt++;
                }
            }
        }
    }
    public void getPipeActs() {
        pipeActs = new ArrayList<PipeAct>();
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
                pipeActs = (ArrayList<PipeAct>) in.readObject();
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
    private void getPupmpActs() {
        pumpActs = new ArrayList<PumpAct>();
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
                pumpActs = (ArrayList<PumpAct>) in.readObject();
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
    private void getDocActs() {
        docActs = new ArrayList<DocAct>();
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
                docActs = (ArrayList<DocAct>) in.readObject();
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
    public void getDirs(){
        System.out.println("Обновление списков");
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
            output = new AbstractMap.SimpleEntry<>("GETALLDIR", 0);
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
                departments = (ArrayList<Dir>) in.readObject();
                for (Dir dir: departments){
                    if(dir.getName().equals("НШ№1")) NH1 = dir.getId();
                    else if(dir.getName().equals("НШ№2")) NH2 = dir.getId();
                    else if(dir.getName().equals("НШ№3")) NH3 = dir.getId();
                    else if(dir.getName().equals("ЦППН")) CPPN = dir.getId();
                    else if(dir.getName().equals("ЦППД")) CPPD = dir.getId();
                }
                System.out.println(departments.size()+": 1");
                departmentObjects = (ArrayList<departmentObject>) in.readObject();
                System.out.println(departmentObjects.size()+": 2");
                employees = (ArrayList<Employee>) in.readObject();
                System.out.println(employees.size()+": 3");
                event_types = (ArrayList<Dir>) in.readObject();
                System.out.println(event_types.size()+": 4");
                event_statuses = (ArrayList<Dir>) in.readObject();
                for (Dir dir: event_statuses){
                    if(dir.getName().equals("Выдача предписания")) idDateTimeStopWorkDoc = dir.getId();
                    else if(dir.getName().equals("Обнаружение неисправности")) idDateTimeStopWork = dir.getId();
                }
                System.out.println("New idDateTimeStopWork - " + idDateTimeStopWork);
                System.out.println(event_statuses.size()+": 5");
                marks = (ArrayList<Dir>) in.readObject();
                System.out.println(marks.size()+": 6");
                pipes = (ArrayList<Dir>) in.readObject();
                System.out.println(pipes.size()+": 7");
//              pumps = (ArrayList<Dir>) in.readObject();
//              System.out.println(pumps.size()+": 8");
                positions = (ArrayList<Dir>) in.readObject();
                System.out.println(positions.size()+": 9");
                posts = (ArrayList<Dir>) in.readObject();
                System.out.println(posts.size()+": 10");
                reasons_stop_pump = (ArrayList<Dir>) in.readObject();
                System.out.println(reasons_stop_pump.size()+": 11");
                statuses_employee = (ArrayList<Dir>) in.readObject();
                System.out.println(statuses_employee.size()+": 12");
                statuses_act = (ArrayList<Dir>) in.readObject();
                for (Dir dir: statuses_act){
                    if(dir.getName().equals("В работе")) ACT_STATUS_JOB = dir.getId();
                    else if(dir.getName().equals("Готово")) STATUS_READY = dir.getId();
                }
                System.out.println("New STATUS_READY - " + STATUS_READY);
                System.out.println(statuses_act.size()+": 13");
                substances = (ArrayList<Dir>) in.readObject();
                System.out.println(substances.size()+": 14");
                types_leak = (ArrayList<Dir>) in.readObject();
                System.out.println(types_leak.size()+": 15");
                types_coating = (ArrayList<Dir>) in.readObject();
                System.out.println(types_coating.size()+": 16");
                types_work_pump = (ArrayList<Dir>) in.readObject();
                System.out.println(types_work_pump.size()+": 17");


            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            Log.d("Clown", e.toString());
        }
        Toast toast = Toast.makeText(getApplicationContext(), "Обновление списков успешно завершено", Toast.LENGTH_LONG);
        toast.show();
        updateArrayString();
    }
    private void updateArrayString() {
        Sdepartments = new ArrayList<>();
        Sdepartment_objects = new ArrayList<>();
        Semployees = new ArrayList<>();
        Sevent_types = new ArrayList<>();
        Sevent_statuses = new ArrayList<>();
        Smarks = new ArrayList<>();
        Strubs = new ArrayList<>();
        Spositions = new ArrayList<>();
        Sreasons_stop_pump = new ArrayList<>();
        Sposts = new ArrayList<>();
        Stypes_coating = new ArrayList<>();
        Stypes_work_pump = new ArrayList<>();
        Sstatuses_employee = new ArrayList<>();
        Sstatuses_act = new ArrayList<>();
        Ssubstances = new ArrayList<>();
        Stypes_leak = new ArrayList<>();

        for (Dir dir: departments) Sdepartments.add(dir.getName());
        for (departmentObject dir: departmentObjects) Sdepartment_objects.add(dir.getName());
        for (Dir dir: event_types) Sevent_types.add(dir.getName());
        for (Dir dir: event_statuses) Sevent_statuses.add(dir.getName());
        for (Dir dir: marks) Smarks.add(dir.getName());
        for (Dir dir: pipes) Strubs.add(dir.getName());
        for (Dir dir: posts) Sposts.add(dir.getName());
        for (Employee emp: employees) for (Dir dir: posts) if(dir.getId() == emp.getIdPost()) Semployees.add(emp.getFIO()+", "+dir.getName());
        for (Dir dir: reasons_stop_pump) Sreasons_stop_pump.add(dir.getName());
        for (Dir dir: types_coating) Stypes_coating.add(dir.getName());
        for (Dir dir: types_work_pump) Stypes_work_pump.add(dir.getName());
        for (Dir dir: statuses_employee) Sstatuses_employee.add(dir.getName());
        for (Dir dir: statuses_act) Sstatuses_act.add(dir.getName());
        for (Dir dir: substances) Ssubstances.add(dir.getName());
        for (Dir dir: types_leak) Stypes_leak.add(dir.getName());
        for (Dir dir: positions) Spositions.add(dir.getName());
    }
}
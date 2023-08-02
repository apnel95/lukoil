package com.example.lukoil;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.lukoil.entity.DocAct;
import com.example.lukoil.entity.PumpAct;
import com.example.lukoil.entity.PipeAct;
import com.example.lukoil.entity.Department_object;
import com.example.lukoil.entity.Dir;
import com.example.lukoil.entity.Employee;
import com.example.lukoil.entity.Event_date_time;
import com.example.lukoil.entity.Remark;
import com.example.lukoil.entity.Work;

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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class GeneralClass extends AppCompatActivity {
    public static ArrayList<PipeAct> pipeActs; public static ArrayList<PumpAct> pumpActs; public static ArrayList<DocAct> docActs;
    public static ArrayList<Event_date_time> this_events; public static ArrayList<Work> this_works; public static ArrayList<Remark> this_remarks; public static ArrayList<Integer> this_works_pump;

    public static ArrayList<Dir> departments; public static ArrayList<Department_object> department_objects; public static ArrayList<Employee> employees;
    public static ArrayList<Dir> event_types; public static ArrayList<Dir> event_statuses; public static ArrayList<Dir> marks;
    public static ArrayList<Dir> trubs; public static ArrayList<Dir> positions; public static ArrayList<Dir> posts;
    public static ArrayList<Dir> reasons_stop_pump; public static ArrayList<Dir> types_coating; public static ArrayList<Dir> types_work_pump;
    public static ArrayList<Dir> statuses_employee; public static ArrayList<Dir> statuses_act; public static ArrayList<Dir> substances;
    public static ArrayList<Dir> types_leak; public static ArrayList<Dir> pumps; //= positions

    public static ArrayList<String> Sdepartments; public static ArrayList<String> Sdepartment_objects; public static ArrayList<String> Semployees;
    public static ArrayList<String> Sevent_types; public static ArrayList<String> Sevent_statuses; public static ArrayList<String> Smarks;
    public static ArrayList<String> Strubs; public static ArrayList<String> Spositions; public static ArrayList<String> Sposts;
    public static ArrayList<String> Sreasons_stop_pump; public static ArrayList<String> Stypes_coating; public static ArrayList<String> Stypes_work_pump;
    public static ArrayList<String> Sstatuses_employee; public static ArrayList<String> Sstatuses_act; public static ArrayList<String> Ssubstances;
    public static ArrayList<String> Stypes_leak; public static ArrayList<String> Spumps; //= Spositions


    public static int ACT_STATUS_JOB = 2, STATUS_READY = 1;

    public static int ID_ACTIVITY_HOME = 0, ID_ACTIVITY_PIPE = 1, ID_ACTIVITY_PUMP = 2, ID_ACTIVITY_DOC = 3, ID_ACTIVITY_PIPE_PLUS = 11, ID_ACTIVITY_PUMP_PLUS = 12, ID_ACTIVITY_DOC_PLUS = 13;
    public static int idDateTimeStopWork = 2, idDateTimeStopWorkDoc = 3;
    public static boolean AutoUpdateDir = true;
    LinearLayout workplace, linearLayoutMenu;
    List<View> workplaceElements;
    EditText dateTime;
    boolean statusMenu;
    int idForm; //0 - home, 1 - trub, 2 - pump, 3 - doc, 11 - trub_plus, 12 - pump_plus, 13 - doc_plus

    Drawable topHome, topTrub, topPump, topDoc, topMenu;
    Context context;
    View viewUpp, viewBottom;
    TextView topTitleActivity;
    TextView home, trub, pump, doc, menu, plus;
    public static int NH1, NH2, NH3, CPPD, CPPN;
    public static int PORT = 29170;
    public static int upPORT = 29171;
    public static String HOST = "192.168.0.16";
    protected void onStartNotHome(int i) {
        super.onStart();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        statusMenu = false;

        linearLayoutMenu = findViewById(R.id.layout_menu);


        viewUpp = findViewById(R.id.upp);
        topTitleActivity = viewUpp.findViewById(R.id.textName);

        dateTime = findViewById(R.id.DateTime);

        viewBottom = findViewById(R.id.bottom);

        home = (TextView) viewBottom.findViewById(R.id.buttonHome);
        trub = (TextView) viewBottom.findViewById(R.id.buttonTrub);
        pump = (TextView) viewBottom.findViewById(R.id.buttonPump);
        doc = (TextView) viewBottom.findViewById(R.id.buttonDoc);
        plus = (TextView) viewBottom.findViewById(R.id.buttonPlus);
        menu = (TextView) viewBottom.findViewById(R.id.buttonMenu);

        Drawable topHome = getResources().getDrawable(R.drawable.home);
        Drawable topTrub = getResources().getDrawable(R.drawable.trub);
        Drawable topPump = getResources().getDrawable(R.drawable.pump);
        Drawable topDoc = getResources().getDrawable(R.drawable.doc);
        Drawable topPlus = getResources().getDrawable(R.drawable.b_new);
        Drawable topMenu = getResources().getDrawable(R.drawable.menu);

        if (i == 0) topHome = getResources().getDrawable(R.drawable.home_red);
        else if (i == 1) topTrub = getResources().getDrawable(R.drawable.trub_red);
        else if (i == 2) topPump = getResources().getDrawable(R.drawable.pump_red);
        else if (i == 3) topDoc = getResources().getDrawable(R.drawable.doc_red);
        else if (i == 11) {
            topTrub = getResources().getDrawable(R.drawable.trub_red);
            topPlus = getResources().getDrawable(R.drawable.b_new_red);
        } else if (i == 12) {
            topPump = getResources().getDrawable(R.drawable.pump_red);
            topPlus = getResources().getDrawable(R.drawable.b_new_red);
        } else if (i == 13) {
            topDoc = getResources().getDrawable(R.drawable.doc_red);
            topPlus = getResources().getDrawable(R.drawable.b_new_red);
        }

        home.setCompoundDrawablesWithIntrinsicBounds(null, topHome, null, null);
        trub.setCompoundDrawablesWithIntrinsicBounds(null, topTrub, null, null);
        pump.setCompoundDrawablesWithIntrinsicBounds(null, topPump, null, null);
        doc.setCompoundDrawablesWithIntrinsicBounds(null, topDoc, null, null);
        plus.setCompoundDrawablesWithIntrinsicBounds(null, topPlus, null, null);
        menu.setCompoundDrawablesWithIntrinsicBounds(null, topMenu, null, null);
    }

    public void toExit(View v) {
        finish();
    }

    public void toHome(View v) {
        Intent Home = new Intent(v.getContext(), MainActivity.class);
        startActivity(Home);
    }

    public void toTrub(View v) {
        System.out.println("ButtonClick Class");
        Intent Trub = new Intent(v.getContext(), Trub_acts.class);
        startActivity(Trub);
    }

    public void toPump(View v) {
        Intent Pump = new Intent(v.getContext(), Pump_acts.class);
        startActivity(Pump);
    }

    public void toDoc(View v) {
        Intent Doc = new Intent(v.getContext(), Doc_acts.class);
        startActivity(Doc);
    }

    public void toPlus(View v) {
    }

    public void toMenu(View v) {
        if (!statusMenu) {
            menu.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.menu_red), null, null);
            linearLayoutMenu.setVisibility(View.VISIBLE);
            statusMenu = true;
        } else {
            menu.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.menu), null, null);
            linearLayoutMenu.setVisibility(View.GONE);
            statusMenu = false;
        }

    }

    public void toView(View v) {
        int id = (int) v.getTag();
        if (id >= 1000 && id < 2000) {
            Intent Trub_view = new Intent(v.getContext(), Trub_view.class);
            PipeAct act1 = new PipeAct();
            for (PipeAct act: pipeActs) if (act.getId() == id-1000) act1 = act;
            Trub_view.putExtra(PipeAct.class.getSimpleName(), getActTrub(act1));
            startActivity(Trub_view);
        } else if (id >= 2000 && id < 3000) {
            Intent Pump_view = new Intent(v.getContext(), Pump_view.class);
            PumpAct act1 = new PumpAct();
            for (PumpAct act: pumpActs) if (act.getId() == id-2000) act1 = act;
            Pump_view.putExtra(PumpAct.class.getSimpleName(), getActPump(act1));
            startActivity(Pump_view);
        } else {
            Intent Doc_view = new Intent(v.getContext(), Doc_view.class);
            DocAct act1 = new DocAct();
            for (DocAct act: docActs) if (act.getId() == id-3000) act1 = act;
            Doc_view.putExtra(DocAct.class.getSimpleName(), getActDoc(act1));
            startActivity(Doc_view);
        }

    }

    public static Date trim(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public String DateToText(Date date) {
        String newDate = "";
        switch (date.getMonth()) {
            case (0):
                newDate = "Январь";
                break;
            case (1):
                newDate = "Февраль";
                break;
            case (2):
                newDate = "Март";
                break;
            case (3):
                newDate = "Апрель";
                break;
            case (4):
                newDate = "Май";
                break;
            case (5):
                newDate = "Июнь";
                break;
            case (6):
                newDate = "Июль";
                break;
            case (7):
                newDate = "Август";
                break;
            case (8):
                newDate = "Сентябрь";
                break;
            case (9):
                newDate = "Октябрь";
                break;
            case (10):
                newDate = "Ноябрь";
                break;
            case (11):
                newDate = "Декабрь";
                break;
        }
        SimpleDateFormat formatForDate = new SimpleDateFormat("dd");
        newDate += ", " + formatForDate.format(date);
        return newDate;
    }
    public PipeAct getActTrub(PipeAct act_trub) {
        final PipeAct act[] = {act_trub};
        Thread thread = new Thread(() -> {
            System.out.println("Waiting for connection2");
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
                Runtime.getRuntime().addShutdownHook(new Thread(() -> {
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
            output = new AbstractMap.SimpleEntry<>("GETACT", act[0]);
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
                act[0] = (PipeAct) in.readObject();
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
        return act[0];
    }

    public PumpAct getActPump(PumpAct act_pump) {
        final PumpAct act[] = {act_pump};
        Thread thread = new Thread(() -> {
            System.out.println("Waiting for connection2");
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
                Runtime.getRuntime().addShutdownHook(new Thread(() -> {
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
            output = new AbstractMap.SimpleEntry<>("GETACT", act[0]);
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
                act[0] = (PumpAct) in.readObject();
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
        return act[0];
    }
    public DocAct getActDoc(DocAct act_doc) {
        final DocAct act[] = {act_doc};
        System.out.println(act[0].getId());
        Thread thread = new Thread(() -> {
            System.out.println("Waiting for connection2");
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
                Runtime.getRuntime().addShutdownHook(new Thread(() -> {
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
            output = new AbstractMap.SimpleEntry<>("GETACT", act[0]);
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
                act[0] = (DocAct) in.readObject();
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
        return act[0];
    }
    public void toNull(View v){
    }
}

package com.example.week04;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Route(value = "/index1")
public class MathView extends FormLayout{
    private TextField num1, num2, ans;
    private Button plus, minus, divide, multiple, mod, max;

    public MathView(){
        HorizontalLayout symbol = new HorizontalLayout();
        VerticalLayout index = new VerticalLayout();
        num1 = new TextField();
        num2 = new TextField();
        ans = new TextField();
        ans.setEnabled(false);
        plus = new Button();
        minus = new Button();
        multiple = new Button();
        divide = new Button();
        mod = new Button();
        max = new Button();
        num1.setLabel("Number 1");
        num2.setLabel("Number 2");
        ans.setLabel("Answer");
        plus.setText("+");
        minus.setText("-");
        multiple.setText("x");
        divide.setText("/");
        mod.setText("Mod");
        max.setText("Max");
        symbol.add(plus, minus, multiple, divide, mod, max);
        index.add(num1, num2, symbol, ans);
        add(index);
        //        function plus
        plus.addClickListener(even -> {
            double num01, num02;
            num01 = Double.parseDouble(num1.getValue());
            num02 = Double.parseDouble(num2.getValue());
            String res = WebClient.create().get().uri("http://localhost:8080/plus/" + num01 + "/" + num02).retrieve().bodyToMono(String.class).block();
            ans.setValue(res);
        });
        //        function minus
        minus.addClickListener(even -> {
            double num01, num02;
            num01 = Double.parseDouble(num1.getValue());
            num02 = Double.parseDouble(num2.getValue());
            String res = WebClient.create().get().uri("http://localhost:8080/minus/" + num01 + "/" + num02).retrieve().bodyToMono(String.class).block();
            ans.setValue(res);
        });
        //        function divide
        divide.addClickListener(even -> {
            double num01, num02;
            num01 = Double.parseDouble(num1.getValue());
            num02 = Double.parseDouble(num2.getValue());
            String res = WebClient.create().get().uri("http://localhost:8080/divide/" + num01 + "/" + num02).retrieve().bodyToMono(String.class).block();
            ans.setValue(res);
        });
        //        function multi
        multiple.addClickListener(even -> {
            double num01, num02;
            num01 = Double.parseDouble(num1.getValue());
            num02 = Double.parseDouble(num2.getValue());
            String res = WebClient.create().get().uri("http://localhost:8080/multi/" + num01 + "/" + num02).retrieve().bodyToMono(String.class).block();
            ans.setValue(res);
        });
        //        function mod
        mod.addClickListener(even -> {
            double num01, num02;
            num01 = Double.parseDouble(num1.getValue());
            num02 = Double.parseDouble(num2.getValue());
            String res = WebClient.create().get().uri("http://localhost:8080/mod/" + num01 + "/" + num02).retrieve().bodyToMono(String.class).block();
            ans.setValue(res);
        });
        //        function max
        max.addClickListener(even -> {
            MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
            data.add("n1", num1.getValue());
            data.add("n2", num2.getValue());
            String res = WebClient.create().post().uri("http://localhost:8080/max").contentType(MediaType.APPLICATION_FORM_URLENCODED).body(BodyInserters.fromFormData(data)).retrieve().bodyToMono(String.class).block();
            ans.setValue(res);
        });
    }

}

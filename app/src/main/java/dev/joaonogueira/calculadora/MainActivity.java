package dev.joaonogueira.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.fathzer.soft.javaluator.DoubleEvaluator;//O Javaluator é uma lib java que permite avaliar expressões matemáticas completas em uma string
//foi adotado pois conforme as teclas vão sendo digitadas, concatenamos os valores e as operações e o Javaluator resolve a expressão toda.
public class MainActivity extends AppCompatActivity {

    private EditText e1,e2; //dois campos EditText, um para a entrada da expressão matemática e outro para a saída
    private int ponto =0;//para controlar que haja apenas um ponto em cada número
    private String expressao ="";//expressão matemática
    private String textoRestante ="";//para juntar um resultado anterior a uma nova expressão
    private Double resultado =0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e1=(EditText)findViewById(R.id.editText1);
        e2=(EditText)findViewById(R.id.editText2);

        e2.setText("0");//ao criar a view, o valor do campo de resultado é zero
    }

    public void onClick(View v)
    {
        switch(v.getId())//aqui, cada tecla pressionada adicionará seu valor à expressão matemática
        {
            case R.id.num0:
                e2.setText(e2.getText()+"0");
                break;

            case R.id.num1:
                e2.setText(e2.getText()+"1");
                break;

            case R.id.num2:
                e2.setText(e2.getText()+"2");
                break;

            case R.id.num3:
                e2.setText(e2.getText()+"3");
                break;


            case R.id.num4:
                e2.setText(e2.getText()+"4");
                break;

            case R.id.num5:
                e2.setText(e2.getText()+"5");
                break;

            case R.id.num6:
                e2.setText(e2.getText()+"6");
                break;

            case R.id.num7:
                e2.setText(e2.getText()+"7");
                break;

            case R.id.num8:
                e2.setText(e2.getText()+"8");
                break;

            case R.id.num9:
                e2.setText(e2.getText()+"9");
                break;

            case R.id.ponto:
                if(ponto ==0 && e2.length()!=0)//aqui avalia-se se não há ponto no número, antes de adicionar um novo
                {
                    e2.setText(e2.getText()+".");
                    ponto++;
                }
                break;

            case R.id.ca://função de limpar os valores
                e1.setText("");
                e2.setText("");
                ponto =0;
                expressao ="";
                break;

            case R.id.somar:
                clicar("+");
                break;

            case R.id.subtrair:
                clicar("-");
                break;

            case R.id.dividir:
                clicar("/");
                break;

            case R.id.multiplicar:
                clicar("*");
                break;

            case R.id.resultado://aqui verificamos se:
                if(e2.length()!=0)//há um valor anterior de saída para ser reaproveitado em uma nova conta
                {
                    textoRestante =e2.getText().toString();
                    expressao =e1.getText().toString()+ textoRestante;//
                }
                e1.setText("");//há uma expressão para ser avaliada,
                if(expressao.length()==0)
                    expressao ="0.0";
                DoubleEvaluator evaluator = new DoubleEvaluator();
                try
                {
                    //O objeto DoubleEvaluator realiza a avaliação da expressão matemática e devolve o resultado
                    resultado =new DoubleEvaluator().evaluate(expressao);
                    e2.setText(resultado +"");
                }
                catch (Exception e)//e se a expressão é matemáticamente válida
                {
                    e2.setText("Expressão inválida");
                    e1.setText("");
                    expressao ="";
                    e.printStackTrace();
                }
                break;

        }
    }

    private void clicar(String op)//função que monta a string da expressão matemática conforme os botões são clicados
    {
        if(e2.length()!=0)
        {
            String text=e2.getText().toString();
            e1.setText(e1.getText() + text+op);
            e2.setText("");
            ponto =0;
        }
        else
        {
            String text=e1.getText().toString();
            if(text.length()>0)
            {
                String newText=text.substring(0,text.length()-1)+op;
                e1.setText(newText);
            }
        }
    }
}

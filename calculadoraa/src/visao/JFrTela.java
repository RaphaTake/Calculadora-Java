package visao;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Enums.EnumOperacao;
import controle.controller;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class JFrTela extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField TfValor;
	private controller controller;
	private EnumOperacao operacaoSelecionada;
	private boolean primeiroClique = true;
	
	
	private double total = 0;
	
	public void setTotal(double valor) {
	    this.total = valor;
	}

	
	public String doubleToString(double valor) {
	    return String.format("%.2f", valor); // Formata com 2 casas decimais
	}

	
	private void processaOperacao(EnumOperacao novaOperacao) {
	    double valorAtual = stringToDouble(TfValor.getText());

	    if (operacaoSelecionada != null) {
	        controller.realizaOperacao(operacaoSelecionada, valorAtual);
	        TfValor.setText(doubleToString(controller.getTotal()));
	    } else {
	        controller.setTotal(valorAtual);
	    }
	    operacaoSelecionada = novaOperacao;
	    TfValor.setText(""); 
	}


	
	private Double stringToDouble(String numero) {
		NumberFormat nf = NumberFormat.getInstance();
		Double dv = 0.0;
		try {
			dv = nf.parse(numero).doubleValue();
		} catch (ParseException ex) {

		}
		return dv;
	}
	/**
	 * Launch the application.
	 */

	private void digita(String caractere) {
		if (TfValor.getText().equals("0,00")) {
			TfValor.setText(caractere);
		} else {
			if (caractere.equals(",") && TfValor.getText().contains(",")) {

			} else {
				TfValor.setText(TfValor.getText().concat(caractere));
			}
		}
	}

	private void limpa() {
		TfValor.setText("0,00");
	}

	/**
	 * Create the frame.
	 */
	public JFrTela() {

		controller = new controller();
		setTitle("Calculadora");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 432, 607);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		TfValor = new JTextField();
		TfValor.setFont(new Font("Tahoma", Font.BOLD, 24));
		TfValor.setHorizontalAlignment(SwingConstants.RIGHT);
		TfValor.setText("0,00");
		TfValor.setBounds(10, 11, 396, 83);
		contentPane.add(TfValor);
		TfValor.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBounds(10, 105, 396, 452);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 4, 0, 0));

		JButton btC = new JButton("C");
		btC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpa();
				controller.zerar();
			}
		});
		btC.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(btC);

		JButton btparenteses = new JButton("( )");
		btparenteses.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
		        if (TfValor.getText().equals("0,00") || TfValor.getText().isEmpty()) {
		            TfValor.setText("");  
		        }
		        
		        if (primeiroClique) {
		            TfValor.setText(TfValor.getText() + "(");  
		            primeiroClique = false; 
		        } else {
		            TfValor.setText(TfValor.getText() + ")");  
		            primeiroClique = true; 
		        }
		    }
		});
		btparenteses.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(btparenteses);

		JButton btporcentagem = new JButton("%");
		btporcentagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double valorAtual = stringToDouble(TfValor.getText());
				controller.setTotal(controller.getTotal() * (valorAtual / 100));
		        TfValor.setText(Double.toString(controller.getTotal()));
			}
		});
		btporcentagem.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(btporcentagem);

		JButton btdivisao = new JButton("÷");
		btdivisao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*controller.realizaOperacao(EnumOperacao.DIVISAO, stringToDouble(TfValor.getText()));
				ultimaOperacao = EnumOperacao.DIVISAO;
				limpa();*/
				processaOperacao(EnumOperacao.DIVISAO);
			}
		});
		btdivisao.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(btdivisao);

		JButton bt7 = new JButton("7");
		bt7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				digita("7");
			}
		});
		bt7.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(bt7);

		JButton bt8 = new JButton("8");
		bt8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				digita("8");
			}
		});
		bt8.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(bt8);

		JButton bt9 = new JButton("9");
		bt9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				digita("9");
			}
		});
		bt9.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(bt9);

		JButton btX = new JButton("X");
		btX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*controller.realizaOperacao(EnumOperacao.MULTIPLICACAO, stringToDouble(TfValor.getText()));
				ultimaOperacao = EnumOperacao.MULTIPLICACAO;
				limpa();*/
				processaOperacao(EnumOperacao.MULTIPLICACAO);
			}
		});
		btX.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(btX);

		JButton bt4 = new JButton("4");
		bt4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				digita("4");
			}
		});
		bt4.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(bt4);

		JButton bt5 = new JButton("5");
		bt5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				digita("5");
			}

		});
		bt5.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(bt5);

		JButton bt6 = new JButton("6");
		bt6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				digita("6");
			}
		});
		bt6.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(bt6);

		JButton btmenos = new JButton("-");
		btmenos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*controller.realizaOperacao(EnumOperacao.SUBTRACAO, stringToDouble(TfValor.getText()));
				ultimaOperacao = EnumOperacao.SUBTRACAO;
				limpa();*/
				processaOperacao(EnumOperacao.SUBTRACAO);
			}
		});
		btmenos.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(btmenos);

		JButton bt1 = new JButton("1");
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				digita("1");
			}

		});
		bt1.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(bt1);

		JButton bt2 = new JButton("2");
		bt2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				digita("2");
			}
		});
		bt2.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(bt2);

		JButton bt3 = new JButton("3");
		bt3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				digita("3");
			}
		});
		bt3.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(bt3);

		JButton btmais = new JButton("+");
		btmais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*controller.realizaOperacao(EnumOperacao.SOMA, stringToDouble(TfValor.getText()));
				ultimaOperacao = EnumOperacao.SOMA;
				limpa();*/
				processaOperacao(EnumOperacao.SOMA);
			}

			/*private Double stringToDouble(String numero) {
				NumberFormat nf = NumberFormat.getInstance();
				Double dv = 0.0;
				try {
					dv = nf.parse(numero).doubleValue();
				} catch (ParseException ex) {

				}
				return dv;
			}*/
		});

		btmais.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(btmais);

		JButton btraiz = new JButton("√");
		btraiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double valorAtual = stringToDouble(TfValor.getText());
				if (valorAtual >0) {
					double resultado = Math.sqrt(valorAtual);
					TfValor.setText(Double.toString(resultado));
				}
				else {
					TfValor.setText("Raiz negativa");
				}
			}
		});
		btraiz.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(btraiz);

		JButton bt0 = new JButton("0");
		bt0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				digita("0");
			}
		});
		bt0.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(bt0);

		JButton btvirgula = new JButton(",");
		btvirgula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				digita(",");
			}
		});
		btvirgula.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(btvirgula);

		JButton btigual = new JButton("=");
		btigual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if (operacaoSelecionada != null) {
			            double valorAtual = stringToDouble(TfValor.getText());
			            controller.realizaOperacao(operacaoSelecionada, valorAtual);
			            TfValor.setText(doubleToString(controller.getTotal()));

			            operacaoSelecionada = null; // Resetar a operação após o cálculo final
			        } else {
			            TfValor.setText("Erro: Escolha uma operação!");
			        }
			    }
			});
			
			/*private String DoubleToString (Double numero) {
				if(numero != null) {
					DecimalFormat formato = new DecimalFormat("##,###,###,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
					formato.setParseBigDecimal(true);
					return formato.format(numero);
				}
				return "";
			}
		});*/
		btigual.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(btigual);
	}
}

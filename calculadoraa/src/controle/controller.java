package controle;

import Enums.EnumOperacao;

public class controller {
	
	private double total = 0;
	
	public controller() {
		total = 0.0;
	}

	    public void setTotal(double valor) {
	        this.total = valor;
	    }
	
	
	/*public Double realizaOperacao(EnumOperacao operacao, Double valor) {
		if (operacao.equals(EnumOperacao.SOMA)) {
			total += valor; //total += total + valor;
		}else if (operacao.equals(EnumOperacao.SUBTRACAO)) {
			total -= valor; 
		}else if (operacao.equals(EnumOperacao.DIVISAO)) {
			total /= valor; 
		}else if (operacao.equals(EnumOperacao.MULTIPLICACAO)) {
			total *= valor; 
		}
		return total;
	}*/
		
	public void realizaOperacao(EnumOperacao operacao, double valor) {
	    System.out.println("Operação: " + operacao + " | Valor: " + valor + " | Total antes: " + total);

	    switch (operacao) {
        case SOMA:
            total += valor;
            break;
        case SUBTRACAO:
            total -= valor;
            break;
        case MULTIPLICACAO:
            total *= valor;
            break;
        case DIVISAO:
            if (valor != 0) {
                total /= valor;
            } else {
                System.out.println("Erro: Divisão por zero!");
            }
            break;
        default:
            System.out.println("Operação inválida!");
    }
}


	
	public Double getTotal() {
		return this.total;
	}
	
	public void zerar() {
		total = 0.0;
	}
	
}

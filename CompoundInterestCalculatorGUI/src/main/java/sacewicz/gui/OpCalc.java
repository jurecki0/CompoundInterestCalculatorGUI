/*
 */
package sacewicz.gui;

/**
 *
 * @author sacew
 */
public class OpCalc {
    public static double investmentVal(double initialBal, double interestRate, 
            int years, int months, int compound, char selected, 
            double amountReinv, int intervalChoice){
        
        double futInvestmentVal = 0;
        
        if(amountReinv==0){
            futInvestmentVal = investWithoutContributions(initialBal, 
                    interestRate, years, months, compound);
        } else if(amountReinv > 0 && selected == 'D'){
            futInvestmentVal = investWithDeposits(initialBal, 
                    interestRate, years, months, compound, amountReinv, 
                    intervalChoice);
        } else if(amountReinv > 0 && selected == 'W'){
            futInvestmentVal = investWithWithdrawals(initialBal, 
                    interestRate, years, months, compound, amountReinv,
                    intervalChoice);
        }
        
        return futInvestmentVal;
    }
    
    public static double investWithDeposits(double initialBal, 
            double interestRate, int years, int months, int compound, 
            double amountReinv, int intervalChoice){
        
        double futInvestmentVal = initialBal;
        double time = years + months/12;
        
        for (double i = 1; i <= time; i++) {
            
        }
        
        return futInvestmentVal;
    }
        
    public static double investWithWithdrawals(double initialBal, 
            double interestRate, int years, int months, int compound, 
            double amountReinv, int intervalChoice){
        
        double futInvestmentVal = initialBal;
        
        return futInvestmentVal;
    }
    
    public static double investWithoutContributions(double initialBal, 
            double interestRate, int years, int months, int compound){
        
        double time = years + (months/12);
        double interest = interestRate/100;
        double futInvestmentVal = initialBal*
                (Math.pow((1+(interest/compound)), compound*time));
        
        return futInvestmentVal;
    }
    
    public static double totalInterestEarned(double futInvestmentVal, 
            double initialBal){
        
        return futInvestmentVal - initialBal;
    }
}

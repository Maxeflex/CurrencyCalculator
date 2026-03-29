// Progammlogik Basis währung Euro, bei GBP -> USD über EURO umrechen: GBP -> EUR -> USD, speicherung als map

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CurrencyService {

    // Hashmap erstellen um Daten die Raten zuspeichern.
    private Map<String, Double> rates = new HashMap<>();

    // Konstruktor fügt einige Standard-Währungen hinzu
    public CurrencyService() {
        rates.put("EUR", 1.0);
        rates.put("USD", 1.16);
        rates.put("GBP", 0.87);
        rates.put("CHF", 0.92);
        rates.put("YEN", 184.12);
    }

    public Set<String> getCurrencies() {
        return rates.keySet();
    }

    public Double getRates(String from) {
        return rates.get(from);
    }

    // Fügt der Hashmap ein neues Key, Value paar hinzu
    public void addCurrency(String name, double rate) {
        // Prüft ob die eingegebene Währung bereits existiert. -> Fehler wenn Sie BEREITS existiert
        if (rates.containsKey(name)) {
            throw new IllegalArgumentException("Currency already exists");
        } else {
            rates.put(name, rate);
        }
    }

    // Überschreibt den Value für den angegebenen Key
    public void updateCurrency(String name, double rate) {
        // Prüft ob die eingegebene Währung existiert. -> Fehler wenn Sie NICHT existiert
        if (rates.containsKey(name)) {
            rates.put(name, rate);
        } else {
            throw new IllegalArgumentException("Currency not found");
        }

    }

    // Rechnet die Währung um, konvertiert vorher die Inputwährung in Euro (Basis-Währung)
    public double convert(double amount, String from, String to) {
        // Prüft ob die eingegebenen Währungen existieren. -> Fehler wenn Sie NICHT existieren
        if (rates.containsKey(from) && rates.containsKey(to)) {
            double fromRate = rates.get(from);
            double toRate = rates.get(to);

            double amountInEur = amount / fromRate;
            double result = amountInEur * toRate;
            return result; //ToDo: Limit result to just 2 decimal places
        } else {
            throw new IllegalArgumentException("Currency not found");
        }
    }
}

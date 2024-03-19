
package execution2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Execution2 {

    public static void main(String[] args) {

        char[] dm = new char[100];
        String[] MS = new String[100];
        int boyut = 0;
        int c = 0;
        int bayrak = 0;
        Scanner sc = new Scanner(System.in);

        int AX = 0, BX = 0, CX = 0, DX = 0;
        int S = 5, Z = 5;

        System.out.println("Dosya ismini giriniz: ");
        String dosyaAdi = sc.nextLine();

        try {
            BufferedReader dosya = new BufferedReader(new FileReader(dosyaAdi));
            String satir;
            StringBuilder dizi = new StringBuilder();

            int b = 0;
            String kar = "";

            while ((satir = dosya.readLine()) != null) {
                for (int i = 0; i < satir.length(); i++) {
                    char karakter = satir.charAt(i);
                    if (karakter == ' ' || karakter == ',' || karakter == ';' || karakter == ':' || karakter == 10) {
                        if (boyut > 0) {
                            for (int a = 0; a < boyut; a++) {
                                kar += dm[a];

                            }
                            MS[b++] = kar;
                            boyut = 0;
                            kar = "";
                        }
                    } else {
                        dm[boyut++] = karakter;
                    }

                }

                if (boyut > 0) {
                    for (int a = 0; a < boyut; a++) {
                        kar += dm[a];
                    }
                    MS[b++] = kar;
                    boyut = 0;
                    kar = "";
                }
            }

            for (int i = 0; i < b; i++) {
                if (MS[i].equals("HRK")) {
                    String targetRegister = MS[i + 1];
                    String sourceRegister = MS[i + 2];

                    switch (targetRegister) {
                        case "AX":
                            switch (sourceRegister) {
                                case "BX":
                                    AX = BX;
                                    break;
                                case "CX":
                                    AX = CX;
                                    break;
                                case "DX":
                                    AX = DX;
                                    break;
                                default:
                                    AX = Integer.parseInt(sourceRegister);
                            }
                            break;

                        case "BX":
                            switch (sourceRegister) {
                                case "AX":
                                    BX = AX;
                                    break;
                                case "CX":
                                    BX = CX;
                                    break;
                                case "DX":
                                    BX = DX;
                                    break;
                                default:
                                    BX = Integer.parseInt(sourceRegister);
                            }
                            break;

                        case "CX":
                            switch (sourceRegister) {
                                case "AX":
                                    CX = AX;
                                    break;
                                case "BX":
                                    CX = BX;
                                    break;
                                case "DX":
                                    CX = DX;
                                    break;
                                default:
                                    CX = Integer.parseInt(sourceRegister);
                            }
                            break;

                        case "DX":
                            switch (sourceRegister) {
                                case "AX":
                                    DX = AX;
                                    break;
                                case "BX":
                                    DX = BX;
                                    break;
                                case "CX":
                                    DX = CX;
                                    break;
                                default:
                                    DX = Integer.parseInt(sourceRegister);
                            }
                            break;
                    }
                } else if (MS[i].equals("TOP")) {
                    String targetRegister = MS[i + 1];
                    String sourceRegister = MS[i + 2];

                    int sourceValue = 0;

                    if (sourceRegister.equals("AX")) {
                        sourceValue = AX;
                    } else if (sourceRegister.equals("BX")) {
                        sourceValue = BX;
                    } else if (sourceRegister.equals("CX")) {
                        sourceValue = CX;
                    } else if (sourceRegister.equals("DX")) {
                        sourceValue = DX;
                    } else {
                        sourceValue = Integer.parseInt(sourceRegister);
                    }

                    switch (targetRegister) {
                        case "AX":
                            AX += sourceValue;
                            break;
                        case "BX":
                            BX += sourceValue;
                            break;
                        case "CX":
                            CX += sourceValue;
                            break;
                        case "DX":
                            DX += sourceValue;
                            break;
                    }
                } else if (MS[i].equals("CIK")) {
                    if (MS[i + 1].equals("AX")) {
                        if (MS[i + 2].equals("BX")) {
                            if (AX > BX) {
                                S = 0;
                                Z = 0;
                            } else if (AX == BX) {
                                S = 0;
                                Z = 1;
                            } else {
                                S = 1;
                                Z = 0;
                            }
                            AX = AX - BX;
                        } else if (MS[i + 2].equals("CX")) {
                            if (AX > CX) {
                                S = 0;
                                Z = 0;
                            } else if (AX == CX) {
                                S = 0;
                                Z = 1;
                            } else {
                                S = 1;
                                Z = 0;
                            }
                            AX = AX - CX;
                        } else if (MS[i + 2].equals("DX")) {
                            if (AX > DX) {
                                S = 0;
                                Z = 0;
                            } else if (AX == DX) {
                                S = 0;
                                Z = 1;
                            } else {
                                S = 1;
                                Z = 0;
                            }
                            AX = AX - DX;
                        } else {//sabitle toplanırsa
                            if (AX > Integer.parseInt(MS[i + 2])) {
                                S = 0;
                                Z = 0;
                            } else if (AX == Integer.parseInt(MS[i + 2])) {
                                S = 0;
                                Z = 1;
                            } else {
                                S = 1;
                                Z = 0;
                            }
                            AX = AX - Integer.parseInt(MS[i + 2]);
                        }
                    }

                    if (MS[i + 1].equals("BX")) {
                        if (MS[i + 2].equals("AX")) {
                            if (BX > AX) {
                                S = 0;
                                Z = 0;
                            } else if (BX == AX) {
                                S = 0;
                                Z = 1;
                            } else {
                                S = 1;
                                Z = 0;
                            }
                            BX = BX - AX;
                        } else if (MS[i + 2].equals("CX")) {
                            if (BX > CX) {
                                S = 0;
                                Z = 0;
                            } else if (BX == CX) {
                                S = 0;
                                Z = 1;
                            } else {
                                S = 1;
                                Z = 0;
                            }
                            BX = BX - CX;
                        } else if (MS[i + 2].equals("DX")) {
                            if (BX > DX) {
                                S = 0;
                                Z = 0;
                            } else if (BX == DX) {
                                S = 0;
                                Z = 1;
                            } else {
                                S = 1;
                                Z = 0;
                            }
                            BX = BX - DX;
                        } else {//sabitle toplanırsa
                            if (BX > Integer.parseInt(MS[i + 2])) {
                                S = 0;
                                Z = 0;
                            } else if (BX == Integer.parseInt(MS[i + 2])) {
                                S = 0;
                                Z = 1;
                            } else {
                                S = 1;
                                Z = 0;
                            }
                            BX = BX - Integer.parseInt(MS[i + 2]);
                        }
                    }
                    if (MS[i + 1].equals("CX")) {
                        if (MS[i + 2].equals("AX")) {
                            if (CX > AX) {
                                S = 0;
                                Z = 0;
                            } else if (CX == AX) {
                                S = 0;
                                Z = 1;
                            } else {
                                S = 1;
                                Z = 0;
                            }
                            CX = CX - AX;
                        } else if (MS[i + 2].equals("BX")) {
                            if (CX > BX) {
                                S = 0;
                                Z = 0;
                            } else if (CX == BX) {
                                S = 0;
                                Z = 1;
                            } else {
                                S = 1;
                                Z = 0;
                            }
                            CX = CX - BX;
                        } else if (MS[i + 2].equals("DX")) {
                            if (CX > DX) {
                                S = 0;
                                Z = 0;
                            } else if (CX == DX) {
                                S = 0;
                                Z = 1;
                            } else {
                                S = 1;
                                Z = 0;
                            }
                            CX = CX - DX;
                        } else {//sabitle toplanırsa
                            if (CX > Integer.parseInt(MS[i + 2])) {
                                S = 0;
                                Z = 0;
                            } else if (CX == Integer.parseInt(MS[i + 2])) {
                                S = 0;
                                Z = 1;
                            } else {
                                S = 1;
                                Z = 0;
                            }
                            CX = CX - Integer.parseInt(MS[i + 2]);
                        }
                    }
                    if (MS[i + 1].equals("DX")) {
                        if (MS[i + 2].equals("AX")) {
                            if (DX > AX) {
                                S = 0;
                                Z = 0;
                            } else if (DX == AX) {
                                S = 0;
                                Z = 1;
                            } else {
                                S = 1;
                                Z = 0;
                            }
                            DX = DX - AX;
                        } else if (MS[i + 2].equals("BX")) {
                            if (DX > BX) {
                                S = 0;
                                Z = 0;
                            } else if (DX == BX) {
                                S = 0;
                                Z = 1;
                            } else {
                                S = 1;
                                Z = 0;
                            }
                            DX = DX - BX;
                        } else if (MS[i + 2].equals("CX")) {
                            if (DX > CX) {
                                S = 0;
                                Z = 0;
                            } else if (DX == CX) {
                                S = 0;
                                Z = 1;
                            } else {
                                S = 1;
                                Z = 0;
                            }
                            DX = DX - CX;
                        } else {//sabitle toplanırsa
                            if (DX > Integer.parseInt(MS[i + 2])) {
                                S = 0;
                                Z = 0;
                            } else if (DX == Integer.parseInt(MS[i + 2])) {
                                S = 0;
                                Z = 1;
                            } else {
                                S = 1;
                                Z = 0;
                            }
                            DX = DX - Integer.parseInt(MS[i + 2]);
                        }
                    }
                } else if (MS[i].equals("CRP")) {
                    String targetRegister = MS[i + 1];
                    String sourceRegister = MS[i + 2];

                    int sourceValue = 0;

                    switch (sourceRegister) {
                        case "AX":
                            sourceValue = AX;
                            break;
                        case "BX":
                            sourceValue = BX;
                            break;
                        case "CX":
                            sourceValue = CX;
                            break;
                        case "DX":
                            sourceValue = DX;
                            break;
                        default:
                            sourceValue = Integer.parseInt(sourceRegister);
                            break;
                    }

                    switch (targetRegister) {
                        case "AX":
                            AX *= sourceValue;
                            break;
                        case "BX":
                            BX *= sourceValue;
                            break;
                        case "CX":
                            CX *= sourceValue;
                            break;
                        case "DX":
                            DX *= sourceValue;
                            break;
                    }
                } else if (MS[i].equals("BOL")) {
                    String targetRegister = MS[i + 1];
                    String sourceRegister = MS[i + 2];

                    int sourceValue = 0;

                    switch (sourceRegister) {
                        case "AX":
                            sourceValue = AX;
                            break;
                        case "BX":
                            sourceValue = BX;
                            break;
                        case "CX":
                            sourceValue = CX;
                            break;
                        case "DX":
                            sourceValue = DX;
                            break;
                        default:
                            sourceValue = Integer.parseInt(sourceRegister);
                            break;
                    }

                    if (sourceValue != 0) {
                        switch (targetRegister) {
                            case "AX":
                                AX /= sourceValue;
                                break;
                            case "BX":
                                BX /= sourceValue;
                                break;
                            case "CX":
                                CX /= sourceValue;
                                break;
                            case "DX":
                                DX /= sourceValue;
                                break;
                        }
                    } else {
                        
                   
                    }
                } else if (MS[i].equals("VE")) {
                    String targetRegister = MS[i + 1];
                    String sourceRegister = MS[i + 2];

                    int sourceValue = 0;

                    switch (sourceRegister) {
                        case "AX":
                            sourceValue = AX;
                            break;
                        case "BX":
                            sourceValue = BX;
                            break;
                        case "CX":
                            sourceValue = CX;
                            break;
                        case "DX":
                            sourceValue = DX;
                            break;
                        default:
                            sourceValue = Integer.parseInt(sourceRegister);
                            break;
                    }

                    switch (targetRegister) {
                        case "AX":
                            AX &= sourceValue;
                            break;
                        case "BX":
                            BX &= sourceValue;
                            break;
                        case "CX":
                            CX &= sourceValue;
                            break;
                        case "DX":
                            DX &= sourceValue;
                            break;
                    }
                } else if (MS[i].equals("VEY")) {
                    String targetRegister = MS[i + 1];
                    String sourceRegister = MS[i + 2];

                    int sourceValue = 0;

                    switch (sourceRegister) {
                        case "AX":
                            sourceValue = AX;
                            break;
                        case "BX":
                            sourceValue = BX;
                            break;
                        case "CX":
                            sourceValue = CX;
                            break;
                        case "DX":
                            sourceValue = DX;
                            break;
                        default:
                            sourceValue = Integer.parseInt(sourceRegister);
                            break;
                    }

                    switch (targetRegister) {
                        case "AX":
                            AX |= sourceValue;
                            break;
                        case "BX":
                            BX |= sourceValue;
                            break;
                        case "CX":
                            CX |= sourceValue;
                            break;
                        case "DX":
                            DX |= sourceValue;
                            break;
                    }
                } else if (MS[i].equals("OKU")) {
                    String targetRegister = MS[i + 1];
                    Scanner scanner = new Scanner(System.in);

                    System.out.println(targetRegister + " in degerini girin: ");

                    int inputValue = scanner.nextInt();

                    switch (targetRegister) {
                        case "AX":
                            AX = inputValue;
                            break;
                        case "BX":
                            BX = inputValue;
                            break;
                        case "CX":
                            CX = inputValue;
                            break;
                        case "DX":
                            DX = inputValue;
                            break;
                    }
                } else if (MS[i].equals("YAZ")) {
                    String targetRegister = MS[i + 1];

                    switch (targetRegister) {
                        case "AX":
                            System.out.println("AX in degeri: " + AX);
                            break;
                        case "BX":
                            System.out.println("BX in degeri: " + BX);
                            break;
                        case "CX":
                            System.out.println("CX in degeri: " + CX);
                            break;
                        case "DX":
                            System.out.println("DX in degeri: " + DX);
                            break;
                        default:
                            System.out.println("Sabitin degeri: " + Integer.parseInt(targetRegister));
                            break;
                    }
                } else if (MS[i].equals("DEG")) {
                    int EX = 2147483647;
                    String targetRegister = MS[i + 1];

                    switch (targetRegister) {
                        case "AX":
                            AX ^= EX;
                            break;
                        case "BX":
                            BX ^= EX;
                            break;
                        case "CX":
                            CX ^= EX;
                            break;
                        case "DX":
                            DX ^= EX;
                            break;
                        default:
                            int k = Integer.parseInt(targetRegister);
                            k ^= EX;
                            break;
                    }
                }
                else if (MS[i].equals("D")) {
                    if (MS[i + 1].equals("ETIKET1")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET1")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET2")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET2")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET3")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET3")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET4")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET4")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET5")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET5")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET6")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET6")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET7")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET7")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET8")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET8")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET9")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET9")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET10")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET10")) {
                                i = x;
                                break;
                            }
                        }
                    }
                }
                else if (MS[i].equals("DB") && (S == 0) && (Z == 0)) {
                    if (MS[i + 1].equals("ETIKET1")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET1")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET2")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET2")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET3")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET3")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET4")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET4")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET5")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET5")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET6")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET6")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET7")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET7")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET8")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET8")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET9")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET9")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET10")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET10")) {
                                i = x;
                                break;
                            }
                        }
                    }
                } else if (MS[i].equals("DK") && (S == 1) && (Z == 0)) {
                    if (MS[i + 1].equals("ETIKET1")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET1")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET2")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET2")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET3")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET3")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET4")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET4")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET5")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET5")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET6")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET6")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET7")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET7")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET8")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET8")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET9")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET9")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET10")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET10")) {
                                i = x;
                                break;
                            }
                        }
                    }
                } else if (MS[i].equals("DBE")) {
                    if (((S==0) && (Z == 0)) || ((S== 0) && (Z==1))) {
                        if (MS[i + 1].equals("ETIKET1")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET1")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET2")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET2")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET3")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET3")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET4")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET4")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET5")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET5")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET6")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET6")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET7")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET7")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET8")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET8")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET9")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET9")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET10")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET10")) {
                                i = x;
                                break;
                            }
                        }
                    }
                } 
                    }
                
                
                else if (MS[i].equals("DKE")) {
                    if (((S==1) && (Z == 0)) || ((S== 0) && (Z==1))) {
                        if (MS[i + 1].equals("ETIKET1")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET1")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET2")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET2")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET3")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET3")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET4")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET4")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET5")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET5")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET6")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET6")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET7")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET7")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET8")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET8")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET9")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET9")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET10")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET10")) {
                                i = x;
                                break;
                            }
                        }
                    }
                } 
                
                else if (MS[i].equals("DE")) {
                    if (((S==0) && (Z == 1))) {
                        if (MS[i + 1].equals("ETIKET1")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET1")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET2")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET2")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET3")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET3")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET4")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET4")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET5")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET5")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET6")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET6")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET7")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET7")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET8")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET8")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET9")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET9")) {
                                i = x;
                                break;
                            }
                        }
                    } else if (MS[i + 1].equals("ETIKET10")) {
                        for (int x = 0; x < MS.length; x++) {
                            if (MS[x].equals("ETIKET10")) {
                                i = x;
                                break;
                            }
                        }
                    }
                } 
                }
                }else if (MS[i].equals("DED")) {
                    if (((S==0) && (Z == 0)) || ((S== 1) && (Z==0))) {
                        switch (MS[i + 1]) {
                            case "ETIKET1":
                                for (int x = 0; x < MS.length; x++) {
                                    if (MS[x].equals("ETIKET1")) {
                                        i = x;
                                        break;
                                    }
                                }       break;
                            case "ETIKET2":
                                for (int x = 0; x < MS.length; x++) {
                                    if (MS[x].equals("ETIKET2")) {
                                        i = x;
                                        break;
                                    }
                                }       break;
                            case "ETIKET3":
                                for (int x = 0; x < MS.length; x++) {
                                    if (MS[x].equals("ETIKET3")) {
                                        i = x;
                                        break;
                                    }
                                }       break;
                            case "ETIKET4":
                                for (int x = 0; x < MS.length; x++) {
                                    if (MS[x].equals("ETIKET4")) {
                                        i = x;
                                        break;
                                    }
                                }       break;
                            case "ETIKET5":
                                for (int x = 0; x < MS.length; x++) {
                                    if (MS[x].equals("ETIKET5")) {
                                        i = x;
                                        break;
                                    }
                                }       break;
                            case "ETIKET6":
                                for (int x = 0; x < MS.length; x++) {
                                    if (MS[x].equals("ETIKET6")) {
                                        i = x;
                                        break;
                                    }
                                }       break;
                            case "ETIKET7":
                                for (int x = 0; x < MS.length; x++) {
                                    if (MS[x].equals("ETIKET7")) {
                                        i = x;
                                        break;
                                    }
                                }       break;
                            case "ETIKET8":
                                for (int x = 0; x < MS.length; x++) {
                                    if (MS[x].equals("ETIKET8")) {
                                        i = x;
                                        break;
                                    }
                                }       break;
                            case "ETIKET9":
                                for (int x = 0; x < MS.length; x++) {
                                    if (MS[x].equals("ETIKET9")) {
                                        i = x;
                                        break;
                                    }
                                }       break;
                            case "ETIKET10":
                                for (int x = 0; x < MS.length; x++) {
                                    if (MS[x].equals("ETIKET10")) {
                                        i = x;
                                        break;
                                    }
                                }       break;
                            default:
                                break;
                        }
                } 
                    }
                

                else if (MS[i].equals("SON")) {
                    System.exit(0);
                }
            }

            dosya.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

package com.hamidrezabashiri.ezcard.utils

import java.util.regex.Pattern
import javax.inject.Inject

class BankNameDetector @Inject constructor() {
    private val IBAN_PREFIX = "IR"

    private val cardNumberToBankMap = mapOf(
        "603799" to "bank_markazi",
        "621986" to "saman",
        "589210" to "sepah",
        "639346" to "sina",
        "627648" to "tosee_saderat",
        "639607" to "sarmayeh",
        "627961" to "sanat_madan",
        "504706" to "shahr",
        "603770" to "keshavarzi",
        "502938" to "dey",
        "628023" to "maskan",
        "603769" to "saderat",
        "627760" to "post",
        "610433" to "mellat",
        "502908" to "tosee_taavon",
        "627383" to "tejarat",
        "627412" to "eghtesad_novin",
        "589463" to "refah",
        "622106" to "parsian",
        "507677" to "noor",
        "502229" to "pasargad",
        "606256" to "melall",
        "639599" to "ghavamin",
        "606373" to "mehr_iran",
        "627488" to "karafarin",
        "505416" to "gardeshgari"
    )

    private val ibanToBankMap = mapOf(
        "010" to "بانک مرکزی جمهوری اسلامی ایران",
        "011" to "بانک صنعت و معدن",
        "012" to "بانک ملت",
        "013" to "بانک رفاه",
        "014" to "بانک مسکن",
        "015" to "بانک سپه",
        "016" to "بانک کشاورزی",
        "017" to "بانک ملی ایران",
        "018" to "بانک تجارت",
        "019" to "بانک صادرات ایران",
        "020" to "بانک توسعه صادرات",
        "021" to "پست بانک ایران",
        "022" to "بانک توسعه تعاون",
        "051" to "موسسه اعتباری توسعه",
        "053" to "بانک کارآفرین",
        "054" to "بانک پارسیان",
        "055" to "بانک اقتصاد نوین",
        "056" to "بانک سامان",
        "057" to "بانک پاسارگاد",
        "058" to "بانک سرمایه",
        "059" to "بانک سینا",
        "060" to "قرض الحسنه مهر",
        "061" to "بانک شهر",
        "062" to "بانک تات",
        "063" to "بانک انصار",
        "064" to "بانک گردشگری",
        "065" to "بانک حکمت ایرانیان",
        "066" to "بانک دی",
        "069" to "بانک ایران زمین"
    )

    /**
     * Detects the bank name based on either an IBAN or a card number.
     *
     * @param input The IBAN or card number to detect the bank for.
     * @return The bank name or an error message if the input is invalid.
     */
    fun detectBank(input: String): String {
        return when {
            input.startsWith(IBAN_PREFIX) -> detectBankFromIBAN(input)
            else -> detectBankFromCardNumber(input)
        }
    }

    private fun detectBankFromCardNumber(cardNumber: String): String {
        for ((cardNum, bankName) in cardNumberToBankMap) {
            val regex = "^$cardNum\\d+".toRegex()
            if (regex.matches(cardNumber)) {
                return bankName
            }
        }
        return "Unknown Bank"
    }

    private fun detectBankFromIBAN(iban: String): String {
        if (!iban.startsWith(IBAN_PREFIX)) {
            return "Invalid IBAN"
        }

        val matcher = Pattern.compile("\\d{2}(\\d{3})\\d+").matcher(iban)
        return if (matcher.find()) {
            val bankCode = matcher.group(1)
            ibanToBankMap[bankCode] ?: "Unknown Bank"
        } else {
            "Invalid IBAN"
        }
    }
}

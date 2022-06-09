//공공데이터 받아오는 데이터 모델
package com.example.project;

import java.util.ArrayList;

public class Model {
    Header header;
    public class Header{
        String resultCode;
        String resultMsg;

        public String getResultCode() {
            return resultCode;
        }

        public void setResultCode(String resultCode) {
            this.resultCode = resultCode;
        }

        public String getResultMsg() {
            return resultMsg;
        }

        public void setResultMsg(String resultMsg) {
            this.resultMsg = resultMsg;
        }
    }
    Body body;
    public class Body{
        String pageNo;
        String totalCount;
        String numOfRows;
        ArrayList<Items> items;

        public String getPageNo() {
            return pageNo;
        }

        public void setPageNo(String pageNo) {
            this.pageNo = pageNo;
        }

        public String getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(String totalCount) {
            this.totalCount = totalCount;
        }

        public String getNumOfRows() {
            return numOfRows;
        }

        public void setNumOfRows(String numOfRows) {
            this.numOfRows = numOfRows;
        }

        public ArrayList<Items> getItems() {
            return items;
        }

        public void setItems(ArrayList<Items> items) {
            this.items = items;
        }

        public class Items{
            String DESC_KOR;
            String SERVING_WT;
            String NUTR_CONT1;
            String NUTR_CONT2;
            String NUTR_CONT3;
            String NUTR_CONT4;
            String NUTR_CONT5;
            String NUTR_CONT6;
            String NUTR_CONT7;
            String NUTR_CONT8;
            String NUTR_CONT9;
            String BGN_YEAR;
            String ANIMAL_PLANT;

            public String getDESC_KOR() {
                return DESC_KOR;
            }

            public void setDESC_KOR(String DESC_KOR) {
                this.DESC_KOR = DESC_KOR;
            }

            public String getSERVING_WT() {
                return SERVING_WT;
            }

            public void setSERVING_WT(String SERVING_WT) {
                this.SERVING_WT = SERVING_WT;
            }

            public String getNUTR_CONT1() {
                return NUTR_CONT1;
            }

            public void setNUTR_CONT1(String NUTR_CONT1) {
                this.NUTR_CONT1 = NUTR_CONT1;
            }

            public String getNUTR_CONT2() {
                return NUTR_CONT2;
            }

            public void setNUTR_CONT2(String NUTR_CONT2) {
                this.NUTR_CONT2 = NUTR_CONT2;
            }

            public String getNUTR_CONT3() {
                return NUTR_CONT3;
            }

            public void setNUTR_CONT3(String NUTR_CONT3) {
                this.NUTR_CONT3 = NUTR_CONT3;
            }

            public String getNUTR_CONT4() {
                return NUTR_CONT4;
            }

            public void setNUTR_CONT4(String NUTR_CONT4) {
                this.NUTR_CONT4 = NUTR_CONT4;
            }

            public String getNUTR_CONT5() {
                return NUTR_CONT5;
            }

            public void setNUTR_CONT5(String NUTR_CONT5) {
                this.NUTR_CONT5 = NUTR_CONT5;
            }

            public String getNUTR_CONT6() {
                return NUTR_CONT6;
            }

            public void setNUTR_CONT6(String NUTR_CONT6) {
                this.NUTR_CONT6 = NUTR_CONT6;
            }

            public String getNUTR_CONT7() {
                return NUTR_CONT7;
            }

            public void setNUTR_CONT7(String NUTR_CONT7) {
                this.NUTR_CONT7 = NUTR_CONT7;
            }

            public String getNUTR_CONT8() {
                return NUTR_CONT8;
            }

            public void setNUTR_CONT8(String NUTR_CONT8) {
                this.NUTR_CONT8 = NUTR_CONT8;
            }

            public String getNUTR_CONT9() {
                return NUTR_CONT9;
            }

            public void setNUTR_CONT9(String NUTR_CONT9) {
                this.NUTR_CONT9 = NUTR_CONT9;
            }

            public String getBGN_YEAR() {
                return BGN_YEAR;
            }

            public void setBGN_YEAR(String BGN_YEAR) {
                this.BGN_YEAR = BGN_YEAR;
            }

            public String getANIMAL_PLANT() {
                return ANIMAL_PLANT;
            }

            public void setANIMAL_PLANT(String ANIMAL_PLANT) {
                this.ANIMAL_PLANT = ANIMAL_PLANT;
            }
        }
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }
}

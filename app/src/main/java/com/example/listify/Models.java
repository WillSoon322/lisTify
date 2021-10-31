package com.example.listify;



public final class Models {


    public final String filter(boolean drop,boolean finish,boolean going,int bintang) {
        String filter = "";
        if (drop || finish || going) {
            filter+= " WHERE ";
            boolean i = false;
            if (drop) {
                filter += "STATUS = 1 ";
                i = true;
            }
            if (finish) {
                if (i) {
                    filter += "OR STATUS = 2 ";
                } else {
                    filter += "STATUS = 2 ";
                }
                i = true;
            }
            if (going) {
                if (i) {
                    filter += "OR STATUS = 0 ";
                } else {
                    filter += "STATUS = 0 ";
                }
            }
            if(bintang !=0) {
                filter += "AND BINTANG = " + bintang;
            }
        } else if (bintang !=0){
                filter += " WHERE BINTANG = " + bintang;
        }
        return filter;
    }



    public String sort(String kolom, boolean desc){
        if(kolom.equals("Alphabet")){
            kolom="JUDUL";
        }else if(kolom.equals("Rating")){
            kolom="BINTANG";
        }else{
            kolom="STATUS";
        }

        String sort=" ORDER BY "+kolom;
        if (!desc){
            sort=sort+" DESC ";
        }
        return sort;
    }

    public String search(String key){
        String search=" WHERE JUDUL LIKE '%"+key+"%' OR SYNOPSIS LIKE '%"+key+"%' OR COMMENT LIKE '%"+key+"%' ";
        return search;
    }



}

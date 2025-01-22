package com.gits.health.HealthApp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {
    @JsonProperty("tag_code")
    private String tagCode;

    private int total;

    private int page;

    private int size;

    private Items items;

    @Data
    public static class Items {
        private List<Product> data;
    }

    @Data
    public static class Product {
        private String name;

        @JsonProperty("kfa_code")
        private String kfaCode;

        private boolean active;

        private String state;

        private String image;

        @JsonProperty("updated_at")
        private String updatedAt;

        @JsonProperty("farmalkes_type")
        private FarmalkesType farmalkesType;

        @JsonProperty("produksi_buatan")
        private String produksiBuatan;

        private String nie;

        @JsonProperty("nama_dagang")
        private String namaDagang;

        private String manufacturer;

        private String registrar;

        private String generik;

        private String rxterm;

        @JsonProperty("dose_per_unit")
        private int dosePerUnit;

        @JsonProperty("fix_price")
        private Double fixPrice;

        @JsonProperty("het_price")
        private Double hetPrice;

        @JsonProperty("farmalkes_hscode")
        private String farmalkesHscode;

        @JsonProperty("tayang_lkpp")
        private Boolean tayangLkpp;

        @JsonProperty("kode_lkpp")
        private String kodeLkpp;

        @JsonProperty("net_weight")
        private Double netWeight;

        @JsonProperty("net_weight_uom_name")
        private String netWeightUomName;

        private Double volume;

        @JsonProperty("volume_uom_name")
        private String volumeUomName;

        private Uom uom;

        @JsonProperty("product_template")
        private ProductTemplate productTemplate;

        private Replacement replacement;

        @Data
        public static class FarmalkesType {
            private String code;
            private String name;
            private String group;
        }

        @Data
        public static class Uom {
            private String name;
        }

        @Data
        public static class ProductTemplate {
            @JsonProperty("kfa_code")
            private String kfaCode;

            private String name;

            private String state;

            private boolean active;

            @JsonProperty("display_name")
            private String displayName;

            @JsonProperty("updated_at")
            private String updatedAt;
        }

        @Data
        public static class Replacement {
            private Object product;
            private Object template;
        }
    }
}

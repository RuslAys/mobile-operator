package ru.javaschool.mobileoperator.domain.enums;

public enum CartItemOperationType {
    SALE{
        @Override
        public String toString() {
            return "Sale";
        }
    },
    SALE_TO_EXIST_CUSTOMER {
        @Override
        public String toString() {
            return "Sale to exist customer";
        }
    },
    ADD_OPTION{
        @Override
        public String toString() {
            return "Add option";
        }
    },
    REMOVE_OPTION{
        @Override
        public String toString() {
            return "Remove option";
        }
    },
    LOCK{
        @Override
        public String toString() {
            return "Lock";
        }
    },
    CHANGE_TARIFF{
        @Override
        public String toString() {
            return "Change tariff";
        }
    },
}

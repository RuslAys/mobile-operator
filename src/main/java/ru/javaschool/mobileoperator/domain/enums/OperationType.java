package ru.javaschool.mobileoperator.domain.enums;

public enum OperationType {
    SALE{
        @Override
        public String toString() {
            return "Sale";
        }
    },
    SALE_TO_EXIST_PERSONAL_ACCOUNT{
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
    ADD_LOCK{
        @Override
        public String toString() {
            return "Add lock";
        }
    },
    REMOVE_LOCK{
        @Override
        public String toString() {
            return "Remove lock";
        }
    },
    CHANGE_TARIFF{
        @Override
        public String toString() {
            return "Change tariff";
        }
    },
}

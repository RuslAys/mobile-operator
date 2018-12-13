package ru.javaschool.mobileoperator.domain.enums;

public enum CartItemOperationType {
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

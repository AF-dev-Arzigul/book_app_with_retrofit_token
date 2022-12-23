package uz.gita.retrofitwithtoken.data.source.local.room.entity

enum class Status(val code: Int) {
    DEFAULT(0),
    ADD(1),
    UPDATE(2),
    DELETE(3);

    companion object {
        fun getStatusByCode(code: Int): Status {
            return Status.values().first {code == it.code}
        }
    }
}
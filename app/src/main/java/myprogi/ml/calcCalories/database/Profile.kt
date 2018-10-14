package myprogi.ml.calcCalories.database

class Profile {

    var id: Int = 0
    var dateTime: String? = null
    var sex: String = "HUMAN"
    var years: Int = 0
    var growth: Int = 0
    var weight: Int = 0
    var activity: String? = null
    var zbu_fats: Int = 0
    var zbu_proteins: Int = 0
    var zbu_carbs: Int = 0

    constructor (id: Int, dateTime: String,
                 sex: String, years: Int,
                 growth: Int, weight: Int,
                 activity: String,
                 zbu_fats: Int, zbu_proteins: Int, zbu_carbs: Int) {
        this.id = id
        this.dateTime = dateTime
        this.sex = sex
        this.years = years
        this.growth = growth
        this.weight = weight
        this.activity = activity
        this.zbu_fats = zbu_fats
        this.zbu_proteins = zbu_proteins
        this.zbu_carbs = zbu_carbs
    }

    constructor (dateTime: String,
                 sex: String, years: Int,
                 growth: Int, weight: Int,
                 activity: String,
                 zbu_fats: Int, zbu_proteins: Int, zbu_carbs: Int) {
        this.dateTime = dateTime
        this.sex = sex
        this.years = years
        this.growth = growth
        this.weight = weight
        this.activity = activity
        this.zbu_fats = zbu_fats
        this.zbu_proteins = zbu_proteins
        this.zbu_carbs = zbu_carbs
    }
    constructor(id:Int, dateTime: String, sex: String) {
        this.id = id
        this.dateTime = dateTime
        this.sex = sex
    }
    constructor(dateTime: String, sex: String) {
        this.id = id
        this.dateTime = dateTime
        this.sex = sex
    }
}
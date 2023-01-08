package main

import (
	"github.com/labstack/echo/v4"
	"github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/adaptor/in/web"
	"github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/adaptor/out/persistence"
	"github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/adaptor/out/persistence/entity"
	"github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/application/service"
	"gorm.io/driver/mysql"
	"gorm.io/gorm"
)

func main() {
	e := echo.New()
	dsn := "root:root!@tcp(mariadb:3306)/backoffice_member?charset=utf8mb4&parseTime=True&loc=Local"
	db, err := gorm.Open(mysql.Open(dsn), &gorm.Config{})
	if err != nil {
		panic(err)
	}

	db.Migrator().DropTable(&entity.Member{})
	db.Migrator().CreateTable(&entity.Member{})
	memberPA := persistence.NewMemberPersistenceAdaptor(db)
	memberService := service.NewMemberService(memberPA, memberPA, memberPA, memberPA)
	web.NewMemberRestController(memberService).RegisterRoutes(e)

	e.Logger.Fatal(e.Start(":8080"))
}

package web

import (
	"github.com/labstack/echo/v4"
	"github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/application/errors"
	"net/http"
)

func handleError(e error, ctx echo.Context) error {
	if e == errors.NotFound {
		return ctx.NoContent(http.StatusNotFound)
	}
	return e
}

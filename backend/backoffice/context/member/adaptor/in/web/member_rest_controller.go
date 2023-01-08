package web

import (
	"github.com/labstack/echo/v4"
	"github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/application/port/in"
	"github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/vo"
	"net/http"
	"strconv"
)

type MemberRestController struct {
	memberUseCase in.MemberUseCase
}

func NewMemberRestController(memberUseCase in.MemberUseCase) *MemberRestController {
	m := &MemberRestController{
		memberUseCase: memberUseCase,
	}
	return m
}

func (c MemberRestController) RegisterRoutes(e *echo.Echo) {
	e.POST("/member", c.registerMember)
	e.GET("/member/:id", c.readBook)
	e.PUT("/member/:id", c.updateBook)
	e.DELETE("/member/:id", c.deleteBook)
}

func (c MemberRestController) registerMember(ctx echo.Context) error {
	var member in.MemberCreateCommand
	if err := ctx.Bind(&member); err != nil {
		return err
	}

	memberId, err := c.memberUseCase.RegisterMember(member)
	if err != nil {
		return err
	}

	return ctx.JSON(http.StatusCreated, memberId)
}

func (c MemberRestController) readBook(ctx echo.Context) error {
	memberId := getMemberIdByCtx(ctx)

	member, err := c.memberUseCase.ReadMember(memberId)
	if err != nil {
		return handleError(err, ctx)
	}

	return ctx.JSON(http.StatusOK, member)
}

func (c MemberRestController) updateBook(ctx echo.Context) error {
	memberId := getMemberIdByCtx(ctx)
	var member in.MemberUpdateCommand
	if err := ctx.Bind(&member); err != nil {
		return err
	}

	err := c.memberUseCase.UpdateMember(memberId, member)
	if err != nil {
		return handleError(err, ctx)
	}

	return ctx.NoContent(http.StatusNoContent)
}

func (c MemberRestController) deleteBook(ctx echo.Context) error {
	memberId := getMemberIdByCtx(ctx)

	err := c.memberUseCase.DeleteMember(memberId)
	if err != nil {
		return err
	}

	return ctx.NoContent(http.StatusNoContent)
}

func getMemberIdByCtx(ctx echo.Context) vo.MemberId {
	idRaw, err := strconv.ParseUint(ctx.Param("id"), 10, 64)
	if err != nil {
		return vo.EmptyMemberId
	} else {
		return vo.MemberId(idRaw)
	}
}

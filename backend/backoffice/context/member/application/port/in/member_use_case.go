package in

import (
	"github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/domain"
	"github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/vo"
)

type MemberUseCase interface {
	RegisterMember(param MemberCreateCommand) (vo.MemberId, error)
	ReadMember(id vo.MemberId) (*MemberReadResult, error)
	UpdateMember(id vo.MemberId, param MemberUpdateCommand) error
	DeleteMember(id vo.MemberId) error
}

type MemberCreateCommand struct {
	Name        string
	PhoneNumber string
}

func (c MemberCreateCommand) MapToMember() *domain.Member {
	return domain.NewMember(c.Name, c.PhoneNumber)
}

type MemberReadResult struct {
	Id          vo.MemberId
	Name        string
	PhoneNumber string
}

func NewMemberReadResultByMember(member *domain.Member) *MemberReadResult {
	return &MemberReadResult{
		Id:          member.Id,
		Name:        member.Name,
		PhoneNumber: member.PhoneNumber,
	}
}

type MemberUpdateCommand struct {
	Name        string
	PhoneNumber string
}

func (c MemberUpdateCommand) MapToMember() *domain.Member {
	return domain.NewMember(c.Name, c.PhoneNumber)
}
